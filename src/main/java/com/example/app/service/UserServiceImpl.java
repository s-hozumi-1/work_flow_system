package com.example.app.service;

import org.springframework.stereotype.Service;

import com.example.app.domain.User;
import com.example.app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void editUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer employeeNumber, int delFlg) {
        userRepository.delete(employeeNumber, delFlg);
    }

    @Override
    public User getByEmployeeNumber(Integer employeeNumber) {
        return userRepository.findByEmployeeNumber(employeeNumber);
    }
}