package org.fastcampus.community_feed.post.repository;

import org.fastcampus.community_feed.post.application.dto.GetPostContentResponseDto;
import org.fastcampus.community_feed.post.repository.entity.post.PostEntity;
import org.fastcampus.community_feed.post.repository.post_queue.UserPostQueueQueryRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("test")
public class FakeUserPostQueueQueryRepository implements UserPostQueueQueryRepository {

    private final FakeUserQueueRedisRepository queueRepository;

    public FakeUserPostQueueQueryRepository(FakeUserQueueRedisRepository queueRepository) {
        this.queueRepository = queueRepository;
    }

    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastContentId) {
        List<PostEntity> postEntities = queueRepository.getPostsByUserId(userId);

        List<GetPostContentResponseDto> result = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            GetPostContentResponseDto content = GetPostContentResponseDto.builder()
                    .id(postEntity.getId())
                    .content(postEntity.getContent())
                    .commentCount(postEntity.getCommentCount())
                    .build();
            result.add(content);
        }
        return result;
    }
}
