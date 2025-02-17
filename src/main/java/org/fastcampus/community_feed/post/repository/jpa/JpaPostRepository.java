package org.fastcampus.community_feed.post.repository.jpa;

import org.fastcampus.community_feed.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaPostRepository extends JpaRepository<PostEntity,Long> {

    @Modifying
    @Query(value = "UPDATE PostEntity p "
        +"SET p.content = :#{#postEntity.getContent()},"
        +"p.state = :#{#postEntity.getState()}, "
        +"p.updDt = now() "
        +"WHERE p.id =:#{#postEntity.id}")
    void updatePostEntity(PostEntity postEntity);

    @Modifying
    @Query("UPDATE PostEntity p "
            +"SET p.likeCount = p.likeCount + :likeCount,"
            +"p.updDt = now() "
            +"WHERE p.id = :postId")
    void updateLikeCount(Long postId,Integer likeCount); //갱신손실 방지 가능, 객체의 상태로 업데이트하는 이전버전의 경우, 두 사용자가 DB에서 같은 시점에 객체정보 가져올 경우 갱신손실 발생, count사용으로 해결
    @Modifying
    @Query("UPDATE PostEntity p "
            +"SET p.commentCount = p.commentCount + 1 "
            +"WHERE p.id = :postId")
    void increaseCommentCounter(Long postId);

    @Query("SELECT p.id FROM PostEntity p WHERE p.author.id = :authorId")
    List<Long> findAllPostIdsByAuthorId(Long authorId);

}
