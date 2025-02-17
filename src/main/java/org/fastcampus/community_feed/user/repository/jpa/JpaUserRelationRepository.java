package org.fastcampus.community_feed.user.repository.jpa;

import org.fastcampus.community_feed.user.repository.entity.UserRelationEntity;
import org.fastcampus.community_feed.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {
    @Query("SELECT u.id.followingUserId FROM UserRelationEntity u WHERE u.id.followerUserId = :userId")
    List<Long> findFollowers(Long userId);
}

