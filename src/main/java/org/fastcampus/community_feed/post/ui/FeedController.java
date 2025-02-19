package org.fastcampus.community_feed.post.ui;

import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.common.principal.AuthPrincipal;
import org.fastcampus.community_feed.common.principal.UserPrincipal;
import org.fastcampus.community_feed.common.ui.Response;
import org.fastcampus.community_feed.post.application.dto.GetPostContentResponseDto;
import org.fastcampus.community_feed.post.repository.post_queue.UserPostQueueQueryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    private final UserPostQueueQueryRepository userPostQueueQueryRepository; //test환경에서는 FakeUserPostQueueQueryRepository사용함

    @GetMapping
    public Response<List<GetPostContentResponseDto>> getPostFeedList(@AuthPrincipal UserPrincipal user, Long lastContentId) {
        List<GetPostContentResponseDto> contentResponse = userPostQueueQueryRepository.getContentResponse(user.getUserId(), lastContentId);
        return Response.ok(contentResponse);
    }
}
