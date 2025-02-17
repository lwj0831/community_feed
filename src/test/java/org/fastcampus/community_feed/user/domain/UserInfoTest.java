package org.fastcampus.community_feed.user.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoTest {

    @Test
    void givenNameAndProfileImage_whenCreated_thenThrowNothing(){
        //given
        String name ="abcd";
        String profileImageUrl="";
        //when
        UserInfo userInfo = new UserInfo(name,profileImageUrl);
        //then
        assertDoesNotThrow(()->new UserInfo(name, profileImageUrl));
    }

    @Test
    void givenBlankNameAndProfileImage_whenCreated_thenThrowException(){
        //given
        String name ="";
        String profileImageUrl="";
        //when
        //then
        assertThrows(IllegalArgumentException.class,()->new UserInfo(name, profileImageUrl));
    }


}