package org.fastcampus.community_feed.user.repository.jpa;

import org.fastcampus.community_feed.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity,Long> {
}
