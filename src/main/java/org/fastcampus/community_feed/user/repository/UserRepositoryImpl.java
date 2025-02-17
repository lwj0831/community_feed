package org.fastcampus.community_feed.user.repository;

import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.user.application.interfaces.UserRepository;
import org.fastcampus.community_feed.user.domain.User;
import org.fastcampus.community_feed.user.repository.entity.UserEntity;
import org.fastcampus.community_feed.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        UserEntity userEntity = new UserEntity(user);
        UserEntity savedEntity = jpaUserRepository.save(userEntity);
        return savedEntity.toUser();
    }

    @Override
    public User findById(Long userId) {
        UserEntity findEntity = jpaUserRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        return findEntity.toUser();
    }
}
