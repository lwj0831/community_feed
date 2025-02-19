package org.fastcampus.community_feed.auth.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.auth.application.interfaces.UserAuthRepository;
import org.fastcampus.community_feed.auth.domain.UserAuth;
import org.fastcampus.community_feed.auth.repository.entity.UserAuthEntity;
import org.fastcampus.community_feed.auth.repository.jpa.JpaUserAuthRepository;
import org.fastcampus.community_feed.user.application.interfaces.UserRepository;
import org.fastcampus.community_feed.user.domain.User;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserAuthRepositoryImpl implements UserAuthRepository {

    private final JpaUserAuthRepository jpaUserAuthRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void registerUser(UserAuth userAuth, User user) {
        User savedUser = userRepository.save(user);
        UserAuthEntity userAuthEntity = new UserAuthEntity(userAuth, savedUser.getId());
        jpaUserAuthRepository.save(userAuthEntity);
    }

    @Override
    public UserAuth findByEmail(String email) {
        UserAuthEntity userAuthEntity = jpaUserAuthRepository.findByEmail(email).orElseThrow();
        return userAuthEntity.toUserAuth();
    }

    @Override
    @Transactional
    public UserAuth loginUser(String email, String password) {
        UserAuthEntity userAuthEntity = jpaUserAuthRepository.findByEmail(email).orElseThrow();
        UserAuth userAuth = userAuthEntity.toUserAuth();
        if (!userAuth.matchPassword(password)) {
            throw new IllegalArgumentException("Invalid password");
        }
        userAuthEntity.updateLastLoginAt(); //dirty checking
        return userAuth;
    }
}
