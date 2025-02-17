package org.fastcampus.community_feed.post.application;

import org.fastcampus.community_feed.post.application.dto.CreatePostRequestDto;
import org.fastcampus.community_feed.post.application.dto.LikeRequestDto;
import org.fastcampus.community_feed.post.application.dto.UpdatePostRequestDto;
import org.fastcampus.community_feed.post.domain.Post;
import org.fastcampus.community_feed.post.domain.PostPublicationState;
import org.fastcampus.community_feed.post.domain.content.Content;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest extends PostServiceTestTemplate {
    CreatePostRequestDto dto = new CreatePostRequestDto(user.getId(), "test-content", PostPublicationState.PUBLIC);

    @Test
    void givenPostRequestDtoWhenCreateThenReturnPost() {
        // when
        Post savedPost = postService.createPost(dto);

        // then
        Post post = postService.getPost(savedPost.getId());
        assertEquals(savedPost, post);
    }

    @Test
    void givenCreatePostWhenUpdateThenReturnUpdatedPost() {
        // given
        Post savedPost = postService.createPost(dto);

        // when
        UpdatePostRequestDto updateDto = new UpdatePostRequestDto(savedPost.getId(), user.getId(), "updated-content", PostPublicationState.PRIVATE);
        Post updatedPost = postService.updatePost(updateDto);

        // then
        Content content = updatedPost.getContent();
        assertEquals("updated-content", content.getContentText());
        assertEquals(PostPublicationState.PRIVATE, updatedPost.getState());
    }

    @Test
    void givenCreatedPostWhenLikedThenReturnPostWithLike() {
        // given
        Post savedPost = postService.createPost(dto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(),otherUser.getId());
        postService.likePost(likeRequestDto);

        // then
        assertEquals(1, savedPost.getLikeCount());
    }

    @Test
    void givenCreatedPostWhenLikedTwiceThenReturnPostWithLike() {
        // given
        Post savedPost = postService.createPost(dto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(),otherUser.getId());
        postService.likePost(likeRequestDto);
        postService.likePost(likeRequestDto);

        // then
        assertEquals(1, savedPost.getLikeCount());
    }

    @Test
    void givenCreatedPostWhenUnlikedThenReturnPostWithoutLike() {
        // given
        Post savedPost = postService.createPost(dto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(),otherUser.getId());
        postService.likePost(likeRequestDto);
        postService.unlikePost(likeRequestDto);

        // then
        assertEquals(0, savedPost.getLikeCount());
    }

    @Test
    void givenCreatedPostWhenUnlikedTwiceThenReturnPostWithoutLike() {
        // given
        Post savedPost = postService.createPost(dto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(),otherUser.getId());
        postService.likePost(likeRequestDto);
        postService.unlikePost(likeRequestDto);
        postService.unlikePost(likeRequestDto);

        // then
        assertEquals(0, savedPost.getLikeCount());
    }
}