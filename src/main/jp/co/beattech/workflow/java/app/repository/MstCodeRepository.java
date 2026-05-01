package jp.co.beattech.workflow.java.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.beattech.workflow.java.app.domain.MstCode;

public interface MstCodeRepository extends JpaRepository<MstCode, String> {
	
    List<MstCode> findByType(String type);
    
    MstCode findByCode(String code);

}