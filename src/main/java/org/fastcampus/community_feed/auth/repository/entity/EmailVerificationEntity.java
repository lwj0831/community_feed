package org.fastcampus.community_feed.auth.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.community_feed.common.repository.TimeBaseEntity;

@Entity
@Table(name="community_email_verification")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmailVerificationEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String token; //이메일 인증용 토큰 - jwt토큰아님
    private boolean isVerified; //이메일 인증 여부

    public EmailVerificationEntity(String email, String token) {
        this.id = null;
        this.email = email;
        this.token = token;
        this.isVerified = false;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void updateToken(String token) {
        this.token = token;
    }

    public void verify() {
        this.isVerified = true;
    }

    public boolean hasSameToken(String token) {
        return this.token.equals(token);
    }
}