package org.fastcampus.community_feed.post.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder //부모 클래스의 필드까지 포함하는 빌더를 자동으로 생성(부모,자식 클래스에 둘 다 애노테이션 적용)
@NoArgsConstructor
@AllArgsConstructor
public class GetPostContentResponseDto extends GetContentResponseDto {
    private Integer commentCount;
}
