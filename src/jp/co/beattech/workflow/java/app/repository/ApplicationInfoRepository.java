package jp.co.beattech.workflow.java.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.beattech.workflow.java.app.domain.ApplicationInfo;

public interface ApplicationInfoRepository extends JpaRepository<ApplicationInfo, Integer> {

    List<ApplicationInfo> findAll();
    
    List<ApplicationInfo> findByApplicationStatusCd(String applicationStatusCd);

    ApplicationInfo findByManagementNumber(Integer managementNumber);
    
    Page<ApplicationInfo> findAll(Pageable pageable);
    
    Page<ApplicationInfo> findByApplicationStatusCd(Pageable pageable,String applicationStatusCd);

    long count();
    
    long countByApplicationStatusCd(String applicationStatusCd);

}