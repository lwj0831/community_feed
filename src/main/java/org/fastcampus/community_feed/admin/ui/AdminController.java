package org.fastcampus.community_feed.admin.ui;

import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.admin.ui.dto.GetTableListResponseDto;
import org.fastcampus.community_feed.admin.ui.dto.posts.GetPostTableRequestDto;
import org.fastcampus.community_feed.admin.ui.dto.posts.GetPostTableResponseDto;
import org.fastcampus.community_feed.admin.ui.dto.users.GetDailyRegisterUserResponseDto;
import org.fastcampus.community_feed.admin.ui.dto.users.GetUserTableRequestDto;
import org.fastcampus.community_feed.admin.ui.dto.users.GetUserTableResponseDto;
import org.fastcampus.community_feed.admin.ui.query.AdminTableQueryRepository;
import org.fastcampus.community_feed.admin.ui.query.UserStatsQueryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController{

    private final UserStatsQueryRepository userStatsQueryRepository;
    private final AdminTableQueryRepository adminTableQueryRepository;

    //메인 페이지
    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        List<GetDailyRegisterUserResponseDto> result = userStatsQueryRepository.getDailyRegisterUserStats(7);
        modelAndView.addObject("result", result);
        return modelAndView;
    }

    @GetMapping("/users")
    public ModelAndView users(GetUserTableRequestDto dto) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("users");

        GetTableListResponseDto<GetUserTableResponseDto> result = adminTableQueryRepository.getUserTable(dto);
        modelAndView.addObject("requestDto", dto);
        modelAndView.addObject("userList", result.getTableList());
        modelAndView.addObject("totalCount", result.getTotalCount());
        return modelAndView;
    }

    @GetMapping("/posts")
    public ModelAndView posts(GetPostTableRequestDto dto) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("posts");

        GetTableListResponseDto<GetPostTableResponseDto> result = adminTableQueryRepository.getPostTable(dto);
        modelAndView.addObject("requestDto", dto);
        modelAndView.addObject("postList", result.getTableList());
        modelAndView.addObject("totalCount", result.getTotalCount());
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


}