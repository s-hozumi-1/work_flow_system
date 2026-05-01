package jp.co.beattech.workflow.java.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jp.co.beattech.workflow.java.app.domain.ApplicationInfo;
import jp.co.beattech.workflow.java.app.repository.ApplicationInfoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationInfoServiceImpl implements ApplicationInfoService {
	
	private final ApplicationInfoRepository applicationInfoRepository;

    @Override
	public List<ApplicationInfo> getAllApplies() {
		return applicationInfoRepository.findAll();
	}

	@Override
	public ApplicationInfo getByManagementNumber(Integer managementNumber) {
		return applicationInfoRepository.findByManagementNumber(managementNumber);
	}



	@Override
	public Page<ApplicationInfo> getApplyListByPage(int page, int numPerPage) {
		return applicationInfoRepository.findAll(PageRequest.of(page - 1, numPerPage));
	}

	@Override
	public Page<ApplicationInfo> getUnapprovedListByPage(int page, int numPerPage,String applicationStatusCd) {
		return applicationInfoRepository.findByApplicationStatusCd(PageRequest.of(page - 1, numPerPage),applicationStatusCd);
	}

	@Override
	public int getTotalApplyPages(int numPerPage) {
		long totalCount = applicationInfoRepository.count();
		return (int) Math.ceil((double) totalCount / numPerPage);
	}
	
	@Override
	public int getTotalUnapprovedPages(int numPerPage, String applicationStatusCd ) {
		long totalCount = applicationInfoRepository.countByApplicationStatusCd(applicationStatusCd);
		return (int) Math.ceil((double) totalCount / numPerPage);
	}

	@Override
	public List<ApplicationInfo> getUnapproveds(String applicationStatusCd) {
		return applicationInfoRepository.findByApplicationStatusCd(applicationStatusCd);
	}

}