package com.example.app.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.app.domain.UserInfo;

public interface UserInfoService {

    List<UserInfo> getAllUsers();

    UserInfo getByUserInfoEmployeeNumber(Integer employeeNumber);

    UserInfo getByLoginId(String loginId);

    Page<UserInfo> getUserListByPage(int page, int numPerPage);

    int getTotalPages(int numPerPage);

}