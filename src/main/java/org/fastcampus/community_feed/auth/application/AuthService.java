package org.fastcampus.community_feed.auth.application;

import org.fastcampus.community_feed.auth.application.dto.CreateUserAuthRequestDto;
import org.fastcampus.community_feed.auth.application.dto.LoginRequestDto;
import org.fastcampus.community_feed.auth.application.dto.UserAccessTokenResponseDto;
import org.fastcampus.community_feed.auth.application.interfaces.EmailVerificationRepository;
import org.fastcampus.community_feed.auth.application.interfaces.UserAuthRepository;
import org.fastcampus.community_feed.auth.domain.Email;
import org.fastcampus.community_feed.auth.domain.TokenProvider;
import org.fastcampus.community_feed.auth.domain.UserAuth;
import org.fastcampus.community_feed.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final TokenProvider tokenProvider;
    private final EmailVerificationRepository emailVerificationRepository;
    private final UserAuthRepository userAuthRepository;

    public AuthService(
            TokenProvider tokenProvider,
            EmailVerificationRepository emailVerificationRepository,
            UserAuthRepository userAuthRepository
    ) {
        this.tokenProvider = tokenProvider;
        this.emailVerificationRepository = emailVerificationRepository;
        this.userAuthRepository = userAuthRepository;
    }


    public UserAccessTokenResponseDto registerUser(CreateUserAuthRequestDto dto) {
        Email email = Email.createEmail(dto.email());

        if (!emailVerificationRepository.isEmailVerified(email)) {  //회원가입 시 이메일 인증 되있어야함
            throw new IllegalArgumentException("Email is not verified");
        }

        UserAuth userAuth = new UserAuth(dto.email(), dto.password(), dto.role());
        User user = new User(dto.name(), dto.profileImageUrl());
        userAuthRepository.registerUser(userAuth, user);

        return createToken(userAuth);
    }

    public UserAccessTokenResponseDto loginUser(LoginRequestDto dto) {
        UserAuth userAuth = userAuthRepository.loginUser(dto.email(), dto.password());
        return createToken(userAuth);
    }

    private UserAccessTokenResponseDto createToken(UserAuth userAuth) {
        String token = tokenProvider.createToken(userAuth.getUserId(), userAuth.getRole());
        return new UserAccessTokenResponseDto(token);
    }
}
