package com.example.app.service;

import java.util.List;

import com.example.app.domain.MstCode;

public interface MstCodeService {
	
    List<MstCode> getCodesByType(String type);
    
    public String findNameByCode(String code) ;
    
}