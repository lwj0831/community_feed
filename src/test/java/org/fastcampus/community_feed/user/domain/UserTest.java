package org.fastcampus.community_feed.user.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private final UserInfo userInfo = new UserInfo("test","");
    private User user1;
    private User user2;

    @BeforeEach
    void init(){
        user1 = new User(1L,userInfo);
        user2 = new User(2L,userInfo);
    }
    @Test
    public void givenTwoUser_whenEqual_thenReturnFalse () throws Exception{
        //when
        boolean isSame = user1.equals(user2);
        //then
        assertFalse(isSame);
    }

    @Test
    public void givenTwoSameIdUser_whenEqual_thenReturnTrue() throws Exception{
        //given
        User user3 = new User(1L,userInfo);
        //when
        boolean isSame = user1.equals(user3);
        //then
        assertTrue(isSame);
    }

    @Test
    public void givenTwoUser_whenHashCode_thenNotEqual () throws Exception{
        //when
        int hashCode1 = user1.hashCode();
        int hashCode2 = user2.hashCode();
        //then
        assertNotEquals(hashCode1,hashCode2);
    }

    @Test
    public void givenTwoSameIdUser_whenHashCode_thenEqual () throws Exception{
        //given
        User user3 = new User(1L,userInfo);
        //when
        int hashCode1 = user1.hashCode();
        int sameUserHashCode = user3.hashCode();
        //then
        assertEquals(hashCode1,sameUserHashCode);
    }

    @Test
    public void givenTwoUser_whenUser1FollowUser2_thenIncreaseUserCount () throws Exception{
        //when
        user1.follow(user2);
        //then
        assertEquals(user1.getFollowingCount(),1);
        assertEquals(user1.getFollowerCount(),0);
        assertEquals(user2.getFollowingCount(),0);
        assertEquals(user2.getFollowerCount(),1);
    }

    @Test
    public void givenTwoUser_whenUser1UnfollowUser2_thenDecreaseUserCount () throws Exception{
        //when
        user1.unfollow(user2);
        //then
        assertEquals(user1.getFollowingCount(),0);
        assertEquals(user1.getFollowerCount(),0);
        assertEquals(user2.getFollowingCount(),0);
        assertEquals(user2.getFollowerCount(),0);
    }


}