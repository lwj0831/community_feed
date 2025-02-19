package org.fastcampus.community_feed.user.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.community_feed.common.domain.PositiveIntegerCounter;
import org.fastcampus.community_feed.common.repository.TimeBaseEntity;
import org.fastcampus.community_feed.user.domain.User;
import org.fastcampus.community_feed.user.domain.UserInfo;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Table(name="community_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@DynamicUpdate //필드 중 변경된 값만 update퀴리에 사용, 불필요하게 모든 필드값 업데이트 하지 않도록 하기 위함
public class UserEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String profileImage;
    private Integer followerCount;
    private Integer followingCount;

    @CreatedDate
    @Column(updatable = false)
    private LocalDate regDate;

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.profileImage = user.getProfileImage();
        this.followerCount = user.getFollowerCount();
        this.followingCount = user.getFollowingCount();
    }

    public User toUser() {
        return User.builder()
                .id(id)
                .info(new UserInfo(name, profileImage))
                .followerCounter(new PositiveIntegerCounter(followerCount))
                .followingCounter(new PositiveIntegerCounter(followingCount))
                .build();
    }
}
