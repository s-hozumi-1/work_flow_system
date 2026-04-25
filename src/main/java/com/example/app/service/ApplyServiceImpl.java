package com.example.app.service;

import org.springframework.stereotype.Service;

import com.example.app.domain.Apply;
import com.example.app.domain.MstCode;
import com.example.app.repository.ApplyRepository;
import com.example.app.repository.MstCodeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplyServiceImpl implements ApplyService {

	private final ApplyRepository applyRepository;
	private final MstCodeRepository mstCodeRepository;

	@Override
	public void update(Integer managementNumber, String applicationStatus) {
        // MstCodeエンティティに変換
	    MstCode statusEntity = mstCodeRepository.findByCode(applicationStatus);
	    if (statusEntity == null) {
	        throw new RuntimeException("Unknown status code: " + applicationStatus);
	    }
	    applyRepository.update(managementNumber, statusEntity);

	}
	
	@Override
	public Apply addApply(Apply apply) {
        return applyRepository.save(apply);
	}

}