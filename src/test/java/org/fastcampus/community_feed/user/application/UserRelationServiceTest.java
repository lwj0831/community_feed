package org.fastcampus.community_feed.user.application;

import org.fastcampus.community_feed.common.FakeObjectFactory;
import org.fastcampus.community_feed.user.application.dto.CreateUserRequestDto;
import org.fastcampus.community_feed.user.application.dto.FollowUserRequestDto;
import org.fastcampus.community_feed.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRelationServiceTest {
    private final UserService userService = FakeObjectFactory.getUserService();
    private final UserRelationService userRelationService = FakeObjectFactory.getUserRelationService();
    private User user1;
    private User user2;
    private FollowUserRequestDto relationDto;

    @BeforeEach
    void init(){
        CreateUserRequestDto createUserRequestDto = new CreateUserRequestDto("test", "");
        user1 = userService.createUser(createUserRequestDto);
        user2 = userService.createUser(createUserRequestDto);
        relationDto =  new FollowUserRequestDto(this.user1.getId(),user2.getId());
    }
    @Test
    public void givenCreateTwoUser_whenFollow_thenUserFollowSaved() throws Exception{
        //given
        //when
        userRelationService.followUser(relationDto);
        //then
        assertEquals(1,user1.getFollowingCount());
        assertEquals(1,user2.getFollowerCount());
    }

    @Test
    void givenFollowUserWhenFollowAgainThenThrowException() {
        // given
        userRelationService.followUser(relationDto);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.followUser(relationDto));
    }

    @Test
    void givenFollowUserWhenFollowSelfThenThrowException() {
        // given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.followUser(sameUser));
    }

    @Test
    void givenFollowUserWhenUnfollowThenUserUnfollowOtherUser() {
        // given
        userRelationService.followUser(relationDto);

        // when
        userRelationService.unfollowUser(relationDto);

        // then
        assertEquals(0, user1.getFollowingCount());
        assertEquals(0, user2.getFollowerCount());
    }

    @Test
    void givenUnfollowUserWhenUnfollowAgainThenThrowException() {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollowUser(relationDto));
    }

    @Test
    void givenUnfollowUserWhenUnfollowSelfThenThrowException() {
        // given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollowUser(sameUser));
    }

}