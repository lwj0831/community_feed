package org.fastcampus.community_feed.post.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.post.application.interfaces.LikeRepository;
import org.fastcampus.community_feed.post.domain.Post;
import org.fastcampus.community_feed.post.domain.comment.Comment;
import org.fastcampus.community_feed.post.repository.entity.comment.CommentEntity;
import org.fastcampus.community_feed.post.repository.entity.like.LikeEntity;
import org.fastcampus.community_feed.post.repository.entity.post.PostEntity;
import org.fastcampus.community_feed.post.repository.jpa.JpaCommentRepository;
import org.fastcampus.community_feed.post.repository.jpa.JpaLikeRepository;
import org.fastcampus.community_feed.post.repository.jpa.JpaPostRepository;
import org.fastcampus.community_feed.user.domain.User;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {

    private final JpaPostRepository jpaPostRepository;
    private final JpaCommentRepository jpaCommentRepository;
    private final JpaLikeRepository jpaLikeRepository;

    @PersistenceContext
    private final EntityManager entityManager;
    @Override
    public boolean checkLike(Post post, User user) {
        LikeEntity entity = new LikeEntity(post, user);
        return jpaLikeRepository.existsById(entity.getId());
    }

    @Override
    public boolean checkLike(Comment comment, User user) {
        LikeEntity entity = new LikeEntity(comment, user);
        return jpaLikeRepository.existsById(entity.getId());
    }

    @Override
    @Transactional
    public void like(Post post, User user) {
        LikeEntity entity = new LikeEntity(post, user); //영속상태가 아닌 새로운 LikeEntity => save시 merge됨
        entityManager.persist(entity); //바로 persist함으로써 merge방지
        /*jpaLikeRepository.save(entity);*/
        jpaPostRepository.updateLikeCount(post.getId(),1); //동시성 문제 체크
        /*jpaPostRepository.save(new PostEntity(post)); //targetPost의 Like 1증가*/
    }

    @Override
    @Transactional
    public void like(Comment comment, User user) {
        LikeEntity entity = new LikeEntity(comment, user);
        entityManager.persist(entity); //바로 persist함으로써 merge방지
        jpaCommentRepository.updateLikeCount(comment.getId(),1); //동시성 문제 체크
        /*jpaCommentRepository.save(new CommentEntity(comment));*/
    }

    @Override
    @Transactional
    public void unlike(Post post, User user) {
        LikeEntity entity = new LikeEntity(post, user);
        jpaLikeRepository.deleteById(entity.getId());//이것도 아마 한번 더 조회쿼리 날아감
        jpaPostRepository.updateLikeCount(post.getId(),-1); //동시성 문제 체크
        /*jpaPostRepository.save(new PostEntity(post));*/
    }

    @Override
    @Transactional
    public void unlike(Comment comment, User user) {
        LikeEntity entity = new LikeEntity(comment, user);
        jpaLikeRepository.deleteById(entity.getId());//이것도 아마 한번 더 조회쿼리 날아감
        jpaCommentRepository.updateLikeCount(comment.getId(),-1); //동시성 문제 체크
        /*jpaCommentRepository.save(new CommentEntity(comment));*/
    }
}
