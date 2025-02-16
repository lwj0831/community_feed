package org.fastcampus.community_feed.post.application.dto;

import org.fastcampus.community_feed.post.domain.PostPublicationState;

public record UpdatePostRequestDto(Long postId, Long userId, String content, PostPublicationState state) {
}
