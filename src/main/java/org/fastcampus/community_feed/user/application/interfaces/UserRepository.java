package org.fastcampus.community_feed.user.application.interfaces;

import org.fastcampus.community_feed.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);
    Optional<User> findById(Long userId);
}
