package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.domain.MstApproval;
import com.example.app.repository.MstApprovalRepository;

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
