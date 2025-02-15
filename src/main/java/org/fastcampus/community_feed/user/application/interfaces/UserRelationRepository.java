package org.fastcampus.community_feed.user.application.interfaces;

import org.fastcampus.community_feed.user.domain.User;

public interface UserRelationRepository { //User와 UserRelation 정보 다른 DB에 저장되는 경우의 유연성 확보를 위해 인터페이스 따로 둠

    boolean isAlreadyFollow(User user, User targetUser);
    void save(User user, User targetUser); //userId안받고 User객체로 받는 이유 생각: User의 count도 같이 업데이트 되어야하기 때문, UserId를 받을 경우 select로 User한번 찾거나 해야됨
    void delete(User user, User targetUser);
}
