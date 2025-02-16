package org.fastcampus.community_feed.user.domain;

import org.fastcampus.community_feed.common.domain.PositiveIntegerCounter;

import java.util.Objects;

public class User {

    private final Long id;
    private final UserInfo info;
    private final PositiveIntegerCounter followingCounter;
    private final PositiveIntegerCounter followerCounter;

    public User(Long id, UserInfo info) {
        if(info==null){
            throw new IllegalStateException();
        }
        this.id = id;
        this.info = info;
        this.followingCounter = new PositiveIntegerCounter();
        this.followerCounter = new PositiveIntegerCounter();
    }

    public void follow(User targetUser){
        if(targetUser.equals(this)){
            throw new IllegalArgumentException();
        }
        followingCounter.increase();
        targetUser.increaseFollowerCounter();
    }

    public void unfollow(User targetUser){
        if(targetUser.equals(this)){
            throw new IllegalArgumentException();
        }
        followingCounter.decrease();
        targetUser.decreaseFollowerCounter();
    }

    private void increaseFollowerCounter(){
        followerCounter.increase();
    }
    private void decreaseFollowerCounter(){
        followerCounter.decrease();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public int getFollowingCount(){
        return followingCounter.getCount();
    }

    public int getFollowerCount(){
        return followerCounter.getCount();
    }

    public UserInfo getInfo() {
        return info;
    }
}
