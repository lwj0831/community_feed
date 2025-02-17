package org.fastcampus.community_feed.post.repository.post_queue;

import org.fastcampus.community_feed.post.application.dto.GetPostContentResponseDto;

import java.util.List;

public interface UserPostQueueQueryRepository {
    List<GetPostContentResponseDto> getContentResponse(Long userId,Long lastContentId);

}
