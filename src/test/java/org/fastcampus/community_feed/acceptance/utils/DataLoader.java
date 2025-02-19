package org.fastcampus.community_feed.acceptance.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.fastcampus.community_feed.auth.application.dto.CreateUserAuthRequestDto;
import org.fastcampus.community_feed.auth.application.dto.SendEmailRequestDto;
import org.springframework.stereotype.Component;

import static org.fastcampus.community_feed.acceptance.steps.SignUpAcceptanceSteps.*;


@Component
public class DataLoader { //DB단의 내용 조회 및 step도 사용

    //entityMange createNativeQuery 사용해서 테스트 객체 생성도 가능
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void loadData() {
        // user 1, 2, 3 생성
        for (int i = 1; i < 4; i++) {
            createUser("user" + i + "@test.com");
        }
    }

    public String getEmailToken(String email) {
        return entityManager.createNativeQuery("SELECT token FROM community_email_verification WHERE email = ?", String.class)
                .setParameter(1, email)
                .getSingleResult()
                .toString();
    }

    public boolean isEmailVerified(String email) {
        return entityManager.createQuery("SELECT isVerified FROM EmailVerificationEntity WHERE email = :email", Boolean.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public Long getUserId(String email) {
        return entityManager.createQuery("SELECT userId FROM UserAuthEntity WHERE email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public void createUser(String email) {
        String password = "password";
        requestSendEmail(new SendEmailRequestDto(email));
        String token = getEmailToken(email);
        requestVerifyEmail(email, token);
        registerUser(new CreateUserAuthRequestDto(email, password, "USER", "nickname", "profile"));
    }
}
