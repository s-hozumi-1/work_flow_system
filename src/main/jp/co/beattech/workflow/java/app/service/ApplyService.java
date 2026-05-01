package jp.co.beattech.workflow.java.app.service;

import org.springframework.stereotype.Service;

import jp.co.beattech.workflow.java.app.domain.Apply;

@Service
public interface ApplyService {
	
	void update(Integer managementNumber , String applicationStatus);
	
	Apply addApply(Apply apply);
	
	
}
