package org.fastcampus.community_feed.acceptance.auth.domain;

import org.fastcampus.community_feed.auth.domain.Password;
import org.fastcampus.community_feed.auth.domain.SHA256;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordTest {

    @Test
    void givenPassword_whenMatchSamePassword_thenReturnTrue() {
        Password password = Password.createEncryptedPassword("password");

        assertTrue(password.matchPassword("password"));
    }

    @Test
    void givenPassword_whenMatchDiffPassword_thenReturnTrue() {
        Password password = Password.createEncryptedPassword("password1");

        assertFalse(password.matchPassword("password"));
    }

    @Test
    void givenPassword_whenMatchNullPassword_thenReturnFalse() {
        Password password = Password.createPassword(SHA256.encrypt("password"));

        assertTrue(password.matchPassword("password"));
    }
}
