package org.fastcampus.community_feed.post.repository.post_queue;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.post.repository.entity.like.QLikeEntity;
import org.fastcampus.community_feed.post.repository.entity.post.PostEntity;
import org.fastcampus.community_feed.post.repository.entity.post.QPostEntity;
import org.fastcampus.community_feed.post.repository.entity.post.UserPostQueueEntity;
import org.fastcampus.community_feed.post.repository.jpa.JpaPostRepository;
import org.fastcampus.community_feed.post.repository.jpa.JpaUserPostQueueRepository;
import org.fastcampus.community_feed.user.repository.entity.UserEntity;
import org.fastcampus.community_feed.user.repository.jpa.JpaUserRelationRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository {
    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;

    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        UserEntity authorEntity = postEntity.getAuthor();
        List<Long> followers = jpaUserRelationRepository.findFollowers(authorEntity.getId());

        List<UserPostQueueEntity> userPostQueueEntities = followers.stream()
                .map(userId -> new UserPostQueueEntity(postEntity.getId(), userId, authorEntity.getId()))
                .toList();

        jpaUserPostQueueRepository.saveAll(userPostQueueEntities);
    }
    @Override
    @Transactional
    public void saveFollowPost(Long userId, Long targetId) {
        List<Long> postIds = jpaPostRepository.findAllPostIdsByAuthorId(targetId);

        List<UserPostQueueEntity> userPostQueueEntities = postIds.stream()
                .map(postId -> new UserPostQueueEntity(postId, userId, targetId))
                .toList();

        jpaUserPostQueueRepository.saveAll(userPostQueueEntities);
    }
    @Override
    @Transactional
    public void deleteUnfollowPost(Long userId, Long targetId) {
        jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetId);
    }
}