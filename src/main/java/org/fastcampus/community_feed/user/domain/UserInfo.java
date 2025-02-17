package org.fastcampus.community_feed.user.domain;

import lombok.Getter;

@Getter
public class UserInfo {
    private final String name;
    private final String profileImageUrl;

    public UserInfo(String name, String profileImageUrl) {
        if(name==null||name.isEmpty()){
            throw new IllegalStateException();
        }
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }

}
