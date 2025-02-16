package org.fastcampus.community_feed.post.domain;

import org.fastcampus.community_feed.post.domain.content.Content;
import org.fastcampus.community_feed.user.domain.User;
import org.fastcampus.community_feed.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    private final User user = new User(1L, new UserInfo("name", "url"));
    private final User otherUser = new User(2L, new UserInfo("name", "url"));

    private final Post post = new Post(1L, user, "content");

    @Test
    void givenPostCreatedWhenLikeThenLikeCountShouldBe1() {
        // when
        post.like(otherUser);

        // then
        assertEquals(1, post.getLikeCount());
    }

    @Test
    void givenPostCreatedWhenLikeByOtherUserThenThrowException() {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> post.like(user));
    }

    @Test
    void givenPostCreatedAndLikeWhenUnlikeThenLikeCountShouldBe0() {
        // given
        post.like(otherUser);

        // when
        post.unlike();

        // then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenPostCreatedWhenUnlikeThenLikeCountShouldBe0() {
        // when
        post.unlike();

        // then
        assertEquals(0, post.getLikeCount());
    }


    @Test
    void givenPostCreatedWhenUpdateContentThenContentShouldBeUpdated() {
        // given
        String newPostContent = "new content";

        // when
        post.updateContent(user, newPostContent);

        // then
        assertEquals(newPostContent, post.getContent());
    }

    @Test
    void givenPostCreatedWhenUpdateContentByOtherUserThenThrowException() {
        // given
        String newPostContent = "new content";

        // when, then
        assertThrows(IllegalArgumentException.class, () -> post.updateContent(otherUser, newPostContent));
    }

    @Test
    void givenPostCreatedWhenUpdateStateThenStateShouldBeUpdated() {
        // given
        PostPublicationState newPostState = PostPublicationState.PRIVATE;

        // when
        post.updateState(newPostState);

        // then
        assertEquals(newPostState, post.getState());
    }

}