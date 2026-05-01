package jp.co.beattech.workflow.java.app.service;

import java.util.List;

import org.springframework.data.domain.Page;

import jp.co.beattech.workflow.java.app.domain.ApplicationInfo;

public interface ApplicationInfoService {

    List<ApplicationInfo> getAllApplies();

    ApplicationInfo getByManagementNumber(Integer managementNumber);

    Page<ApplicationInfo> getApplyListByPage(int page, int numPerPage);
    
    Page<ApplicationInfo> getUnapprovedListByPage(int page, int numPerPage,String applicationStatusCd);
    
    int getTotalApplyPages(int numPerPage);
    
    int getTotalUnapprovedPages(int numPerPage,String applicationStatusCd);
    
    List<ApplicationInfo> getUnapproveds(String applicationStatusCd);

}