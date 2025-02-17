package org.fastcampus.community_feed.user.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.community_feed.common.repository.TimeBaseEntity;

@Entity
@Table(name="community_user_relation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserRelationEntity extends TimeBaseEntity {

    @EmbeddedId
    private UserRelationIdEntity id;
}