package org.fastcampus.community_feed.post.repository.post_queue;

import org.fastcampus.community_feed.post.repository.entity.post.PostEntity;

import java.util.List;

public interface UserQueueRedisRepository {
    void publishPostToUserListQueue(PostEntity post, List<Long> userIdList);
    void publishPostListToUserQueue(List<PostEntity> postEntities, Long userId);
    void deletePostToUserQueue(Long userId, Long targetUserId);
}
