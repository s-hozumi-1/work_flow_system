package jp.co.beattech.workflow.java.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.beattech.workflow.java.app.domain.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    List<UserInfo> findAll();

    UserInfo findByEmployeeNumber(Integer employeeNumber);
    
    UserInfo findByLoginId(String loginId);
    
    Page<UserInfo> findAll(Pageable pageable);

    long count();

}