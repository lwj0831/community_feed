package org.fastcampus.community_feed.post.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.post.application.interfaces.CommentRepository;
import org.fastcampus.community_feed.post.domain.Post;
import org.fastcampus.community_feed.post.domain.comment.Comment;
import org.fastcampus.community_feed.post.repository.entity.comment.CommentEntity;
import org.fastcampus.community_feed.post.repository.jpa.JpaCommentRepository;
import org.fastcampus.community_feed.post.repository.jpa.JpaPostRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {
    private final JpaPostRepository jpaPostRepository;
    private final JpaCommentRepository jpaCommentRepository;

    @Override
    public Comment findById(Long id) {
        CommentEntity entity = jpaCommentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        return entity.toComment();
    }

    @Override
    @Transactional
    public Comment save(Comment comment) {
        CommentEntity commentEntity = new CommentEntity(comment);
        Post targetPost = comment.getPost();
        if(comment.getId()!=null){ //merge방지위해 직접 만든 update쿼리 사용
            jpaCommentRepository.updateCommentEntity(commentEntity);
            return commentEntity.toComment();
        }
        commentEntity = jpaCommentRepository.save(commentEntity); //Id없는 DB에 아직 처음 저장되는 CommentEntity의 경우 persist일어나므로 save써도 무방(select문 2번안나감)
        jpaPostRepository.increaseCommentCounter(targetPost.getId()); //targetPost의 Comment수 1증가
        return commentEntity.toComment();
    }
}
