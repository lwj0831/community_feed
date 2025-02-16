package org.fastcampus.community_feed.user.application;

import org.fastcampus.community_feed.user.application.dto.CreateUserRequestDto;
import org.fastcampus.community_feed.user.application.interfaces.UserRepository;
import org.fastcampus.community_feed.user.domain.User;
import org.fastcampus.community_feed.user.domain.UserInfo;
import org.fastcampus.community_feed.user.repository.FakeUserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);
    
    @Test
    public void givenUserInfoDto_whenCreateUser_thenFindUser() throws Exception{
        //given
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");
        //when
        User user = userService.createUser(dto);

        //then
        User findUser = userService.getUser(user.getId());
        UserInfo findUserInfo = findUser.getInfo();
        assertEquals(user.getId(),findUser.getId());
        assertEquals(findUserInfo.getName(),"test");

    }


}