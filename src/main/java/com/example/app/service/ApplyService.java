package com.example.app.service;

import org.springframework.stereotype.Service;

import com.example.app.domain.Apply;

@Service
public interface ApplyService {
	
	void update(Integer managementNumber , String applicationStatus);
	
	Apply addApply(Apply apply);
	
	
}
