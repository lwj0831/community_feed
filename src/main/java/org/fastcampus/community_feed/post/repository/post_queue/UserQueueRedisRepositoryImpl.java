package org.fastcampus.community_feed.post.repository.post_queue;

import org.fastcampus.community_feed.post.repository.entity.post.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserQueueRedisRepositoryImpl implements UserQueueRedisRepository{

    @Override
    public void publishPostToUserListQueue(PostEntity post, List<Long> userIdList) {

    }

    @Override
    public void publishPostListToUserQueue(List<PostEntity> postEntities, Long userId) {

    }

    @Override
    public void deletePostToUserQueue(Long userId, Long targetUserId) {

    }
}
