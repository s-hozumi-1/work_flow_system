package com.example.app.service;

import java.util.List;

import com.example.app.domain.MstApproval;

public interface MstApprovalService {
	
    List<MstApproval> getEmployeeNumberByLevel(Integer level);
    
    
}