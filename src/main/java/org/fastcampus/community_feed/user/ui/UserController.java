package org.fastcampus.community_feed.user.ui;

import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.common.ui.Response;
import org.fastcampus.community_feed.user.application.UserService;
import org.fastcampus.community_feed.user.application.dto.CreateUserRequestDto;
import org.fastcampus.community_feed.user.application.dto.GetUserListResponseDto;
import org.fastcampus.community_feed.user.application.dto.GetUserResponseDto;
import org.fastcampus.community_feed.user.domain.User;
import org.fastcampus.community_feed.user.repository.jpa.JpaUserListQueryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JpaUserListQueryRepository userListEntityQuery; //Service거치지않고 바로 컨트롤러도 가능, 이 경우 조회만 가능한 쿼리레포지토리

    @PostMapping
    public Response<Long> createUser(@RequestBody CreateUserRequestDto dto) {
        User user = userService.createUser(dto);
        return Response.ok(user.getId());
    }

    @GetMapping("/{userId}")
    public Response<GetUserResponseDto> getUserResponse(@PathVariable(name = "userId") Long id) {
        return Response.ok(userService.getUserProfile(id));
    }

    @GetMapping("/{userId}/follower")
    public Response<List<GetUserListResponseDto>> getFollowerList(@PathVariable(name = "userId") Long id) {
        return Response.ok(userListEntityQuery.getFollowerList(id));
    }

    @GetMapping("/{userId}/following")
    public Response<List<GetUserListResponseDto>> getFollowingList(@PathVariable(name = "userId") Long id) {
        return Response.ok(userListEntityQuery.getFollowingList(id));
    }

}