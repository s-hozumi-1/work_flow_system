package jp.co.beattech.workflow.java.app.service;

public interface LoginService {
	
	boolean isCorrectIdAndPassword(String loginId, String password);
}
