package jp.co.beattech.workflow.java.app.service;

import java.util.List;

import org.springframework.data.domain.Page;

import jp.co.beattech.workflow.java.app.domain.UserInfo;

public interface UserInfoService {

    List<UserInfo> getAllusers();

    UserInfo getByUserInfoEmployeeNumber(Integer employeeNumber);

    UserInfo getByLoginId(String loginId);

    Page<UserInfo> getUserListByPage(int page, int numPerPage);

    int getTotalPages(int numPerPage);

}