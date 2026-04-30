package jp.co.beattech.workflow.java.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jp.co.beattech.workflow.java.app.domain.UserInfo;
import jp.co.beattech.workflow.java.app.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;

    @Override
    public List<UserInfo> getAllUsers() {
        return userInfoRepository.findAll();
    }

    @Override
    public UserInfo getByUserInfoEmployeeNumber(Integer employeeNumber) {
        return userInfoRepository.findByEmployeeNumber(employeeNumber);
    }

    @Override
    public UserInfo getByLoginId(String loginId) {
        return userInfoRepository.findByLoginId(loginId);
    }

    @Override
    public Page<UserInfo> getUserListByPage(int page, int numPerPage) {
        return userInfoRepository.findAll(PageRequest.of(page - 1, numPerPage));
    }

    @Override
    public int getTotalPages(int numPerPage) {
        long totalCount = userInfoRepository.count();
        return (int) Math.ceil((double) totalCount / numPerPage);
    }
}