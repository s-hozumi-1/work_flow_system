package jp.co.beattech.workflow.java.app.service;

import java.util.List;

import jp.co.beattech.workflow.java.app.domain.MstApproval;

public interface MstApprovalService {
	
    List<MstApproval> getEmployeeNumberByLevel(Integer level);
    
    
}