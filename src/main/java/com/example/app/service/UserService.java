package com.example.app.service;

import org.springframework.stereotype.Service;

import com.example.app.domain.User;

@Service
public interface UserService {

	
	void addUser(User user);
	
	void editUser(User user);
	
	void deleteUser(Integer employeeNumber , int delFlg);
	
	User getByEmployeeNumber(Integer employeeNumber);
}
