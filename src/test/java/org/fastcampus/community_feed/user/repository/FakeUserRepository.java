package org.fastcampus.community_feed.user.repository;

import org.fastcampus.community_feed.user.application.interfaces.UserRepository;
import org.fastcampus.community_feed.user.domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeUserRepository implements UserRepository {
    private final Map<Long,User> store = new HashMap<>();

    @Override
    public User save(User user) {
        if(user.getId()!=null){
            store.put(user.getId(),user);
        }
        Long newId = store.size()+1L;
        User newUser = new User(newId, user.getInfo());
        store.put(newId,newUser);
        return newUser;
    }

    @Override
    public Optional<User> findById(Long userId) {
        return Optional.ofNullable(store.get(userId));
    }
}
