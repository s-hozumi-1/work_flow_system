package jp.co.beattech.workflow.java.app.service;

import org.springframework.stereotype.Service;

import jp.co.beattech.workflow.java.app.domain.User;

@Service
public interface UserService {

	
	void addUser(User user);
	
	void editUser(User user);
	
	void deleteUser(Integer employeeNumber , int delFlg);
	
	User getByEmployeeNumber(Integer employeeNumber);
}
