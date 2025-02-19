package org.fastcampus.community_feed.acceptance.auth;

import org.fastcampus.community_feed.acceptance.utils.AcceptanceTestTemplate;
import org.fastcampus.community_feed.post.application.dto.CreatePostRequestDto;
import org.fastcampus.community_feed.post.application.dto.GetPostContentResponseDto;
import org.fastcampus.community_feed.post.domain.PostPublicationState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.fastcampus.community_feed.acceptance.steps.FeedAcceptanceSteps.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeedAcceptanceTest extends AcceptanceTestTemplate {

    private String token;
    /**
     * User 1 --- follow ---> User 2
     * User 1 --- follow ---> User 3
     */
    @BeforeEach
    void init() {
        super.setUp();
        this.token = login("user1@test.com");
    }

    /**
     * User 2 create Post 1
     * User 1 Get Post 1 From Feed
     */
    @Test
    void givenUserHasFollowerWhenCreatePostThenFollowerFeedCanGetPost() {
        // given
        CreatePostRequestDto dto = new CreatePostRequestDto(2L, "1 content", PostPublicationState.PUBLIC);
        Long createdPostId = requestCreatePost(dto);

        // when, 팔로워의 피드 요청
        List<GetPostContentResponseDto> result = requestFeedList(this.token);

        // then
        assertEquals(1, result.size());
        assertEquals(createdPostId, result.get(0).getId());
    }

    @Test
    void givenUserHasFollowerAndCreatePostWhenGetPostThenReturnPostWithInvalidToken() {
        // when, 팔로워의 피드 요청
        Integer resultCode = requestFeedCode("invalid token");

        // then
        assertEquals(400, resultCode);
    }
}
