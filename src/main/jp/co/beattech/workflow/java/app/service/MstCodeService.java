package jp.co.beattech.workflow.java.app.service;

import java.util.List;

import jp.co.beattech.workflow.java.app.domain.MstCode;

public interface MstCodeService {
	
    List<MstCode> getCodesByType(String type);
    
    public String findNameByCode(String code) ;
    
}