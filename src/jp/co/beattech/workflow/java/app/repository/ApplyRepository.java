package jp.co.beattech.workflow.java.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import jp.co.beattech.workflow.java.app.domain.Apply;
import jp.co.beattech.workflow.java.app.domain.MstCode;

public interface ApplyRepository extends JpaRepository<Apply, Integer> {

    // insert と update は JpaRepository の save() で代用可能なのでメソッドは不要

    @Modifying
    @Transactional    
    @Query("UPDATE Apply u SET u.applicationStatus = :applicationStatus WHERE u.managementNumber = :managementNumber")
    void update(@Param("managementNumber") Integer managementNumber, @Param("applicationStatus") MstCode applicationStatus );
    
}