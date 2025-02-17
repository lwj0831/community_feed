package org.fastcampus.community_feed.post.ui;

import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.common.ui.Response;
import org.fastcampus.community_feed.post.application.CommentService;
import org.fastcampus.community_feed.post.application.dto.CreateCommentRequestDto;
import org.fastcampus.community_feed.post.application.dto.LikeRequestDto;
import org.fastcampus.community_feed.post.application.dto.UpdateCommentRequestDto;
import org.fastcampus.community_feed.post.domain.comment.Comment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public Response<Long> createComment(@RequestBody CreateCommentRequestDto dto) {
        Comment comment = commentService.createComment(dto);
        return Response.ok(comment.getId());
    }

    @PatchMapping
    public Response<Long> updateComment(@RequestBody UpdateCommentRequestDto dto) {
        Comment comment = commentService.updateComment(dto);
        return Response.ok(comment.getId());
    }

    @PostMapping("/like")
    public Response<Void> likeComment(@RequestBody LikeRequestDto dto) {
        commentService.likeComment(dto);
        return Response.ok(null);
    }

    @PostMapping("/unlike")
    public Response<Void> unlikeComment(@RequestBody LikeRequestDto dto) {
        commentService.unlikeComment(dto);
        return Response.ok(null);
    }

}
