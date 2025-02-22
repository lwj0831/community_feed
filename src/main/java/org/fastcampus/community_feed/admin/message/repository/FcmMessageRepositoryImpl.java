package org.fastcampus.community_feed.admin.message.repository;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.admin.message.application.interfaces.MessageRepository;
import org.fastcampus.community_feed.admin.message.repository.entity.FcmTokenEntity;
import org.fastcampus.community_feed.admin.message.repository.jpa.JpaFcmTokenRepository;
import org.fastcampus.community_feed.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FcmMessageRepositoryImpl implements MessageRepository {

    private final JpaFcmTokenRepository jpaFcmTokenRepository;

    // 유저 이름을 넣어줄 수 있는 기본 템플릿, a 유저가 b 유저의 글에 좋아요를 눌렀다는 메시지
    private static final String LIKE_MESSAGE_TEMPLATE = "%s님이 %s님 글에 좋아요를 눌렀습니다.";
    private static final String MESSAGE_KEY = "message";

    @Override
    public void sendLikeMessage(User sender, User target) {
        Optional<FcmTokenEntity> tokenEntity = jpaFcmTokenRepository.findById(target.getId());
        if (tokenEntity.isEmpty()) {
            return;
        }

        FcmTokenEntity token = tokenEntity.get();
        Message message = Message.builder()
                .putData(MESSAGE_KEY, LIKE_MESSAGE_TEMPLATE.formatted(sender.getName(), target.getName()))
                .setToken(token.getToken())
                .build();
        FirebaseMessaging.getInstance().sendAsync(message);
    }
}
