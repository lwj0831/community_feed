package org.fastcampus.community_feed.acceptance.utils;

import org.fastcampus.community_feed.user.application.dto.CreateUserRequestDto;
import org.fastcampus.community_feed.user.application.dto.FollowUserRequestDto;
import org.springframework.stereotype.Component;

import static org.fastcampus.community_feed.acceptance.steps.UserAcceptanceSteps.createUser;
import static org.fastcampus.community_feed.acceptance.steps.UserAcceptanceSteps.followUser;


@Component
public class DataLoader {

    //entityMange createNativeQuery 사용해서 테스트 객체 생성도 가능
    public void loadData() {
        // user 1, 2, 3 생성 및 user 1 follow user 2, user 3
        CreateUserRequestDto user = new CreateUserRequestDto("user", null);
        createUser(user);
        createUser(user);
        createUser(user);

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(2L, 3L));
    }
}
