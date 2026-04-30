package jp.co.beattech.workflow.java.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.beattech.workflow.java.app.domain.MstApproval;
import jp.co.beattech.workflow.java.app.repository.MstApprovalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MstApprovalServiceImpl implements MstApprovalService {
	
	private final MstApprovalRepository mstApprovalRepository;

	@Override
	public List<MstApproval> getEmployeeNumberByLevel(Integer level) {
		return mstApprovalRepository.findByLevel(level);
	}

}
