package com.example.app.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.app.domain.UserInfo;
import com.example.app.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserInfoRepository userInfoRepository;

    @Override
    public boolean isCorrectIdAndPassword(String loginId, String password) {

        UserInfo user = userInfoRepository.findByLoginId(loginId);

        if (user == null) {
            System.out.println("user == null");
            return false;
        }

        if (!BCrypt.checkpw(password, user.getPassword())) {
            return false;
        }

        return true;
    }
}