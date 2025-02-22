package org.fastcampus.community_feed.acceptance.utils;

import org.fastcampus.community_feed.auth.application.dto.LoginRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.fastcampus.community_feed.acceptance.steps.LoginAcceptanceSteps.requestLoginGetToken;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AcceptanceTestTemplate {

    @Autowired
    private DatabaseCleanup databaseCleanup;
    @Autowired
    private DataLoader dataLoader;

    @BeforeEach //각 테스트 코드 실행 전(이 클래스는 테스트 클래스 아니므로 해당 템플릿 상속받는 test클래스에서 수행됨)
    public void setUp() {
        databaseCleanup.execute();
        dataLoader.loadData();
    }

    public void cleanUp() {
        databaseCleanup.execute();
    }

    protected String getEmailToken(String email) {
        return dataLoader.getEmailToken(email);
    }

    protected boolean isEmailVerified(String email) {
        return dataLoader.isEmailVerified(email);
    }

    protected Long getUserId(String email) {
        return dataLoader.getUserId(email);
    }

    protected String login(String email) {
        return requestLoginGetToken(new LoginRequestDto(email, "password","token"));
    }

    protected void createUser(String email) {
        dataLoader.createUser(email);
    }

}
