package org.fastcampus.community_feed.user.application;

import org.fastcampus.community_feed.user.application.dto.FollowUserRequestDto;
import org.fastcampus.community_feed.user.application.interfaces.UserRelationRepository;
import org.fastcampus.community_feed.user.domain.User;

public class UserRelationService {
    private final UserService userService;
    private final UserRelationRepository userRelationRepository;

    public UserRelationService(UserRelationRepository userRelationRepository, UserService userService) {
        this.userService = userService;
        this.userRelationRepository = userRelationRepository;
    }

    public void followUser(FollowUserRequestDto dto){
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if(userRelationRepository.isAlreadyFollow(user,targetUser)){
            throw new IllegalArgumentException();
        }
        user.follow(targetUser);
        userRelationRepository.save(user,targetUser);
    }

    public void unfollowUser(FollowUserRequestDto dto){
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if(!userRelationRepository.isAlreadyFollow(user,targetUser)){
            throw new IllegalArgumentException();
        }
        user.unfollow(targetUser);
        userRelationRepository.delete(user,targetUser);
    }

}
