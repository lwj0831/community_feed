package org.fastcampus.community_feed.post.repository.entity.post;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.community_feed.common.domain.PositiveIntegerCounter;
import org.fastcampus.community_feed.common.repository.TimeBaseEntity;
import org.fastcampus.community_feed.post.domain.Post;
import org.fastcampus.community_feed.post.domain.PostPublicationState;
import org.fastcampus.community_feed.post.domain.content.PostContent;
import org.fastcampus.community_feed.user.repository.entity.UserEntity;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="community_post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostEntity extends TimeBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="author_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))//원치 않는 인덱싱, 데이터 수정 제약 방지
    private UserEntity author;

    private String content;

    @Convert(converter = PostPublicationStateConverter.class) //복잡한 객체가 아닌 Enum정도는 @Enumerated사용해도 무방
    private PostPublicationState state;
    private Integer likeCount;

    @ColumnDefault("0")
    private int commentCount;

    public PostEntity(Post post) {
        this.id = post.getId();
        this.author = new UserEntity(post.getAuthor());
        this.content = post.getContentText();
        this.state = post.getState();
        this.likeCount = post.getLikeCount();
    }

    public Post toPost() {
        return Post.builder()
                .id(id)
                .author(author.toUser())
                .content(new PostContent(content))
                .state(state)
                .likeCounter(new PositiveIntegerCounter(likeCount))
                .build();
    }
}
