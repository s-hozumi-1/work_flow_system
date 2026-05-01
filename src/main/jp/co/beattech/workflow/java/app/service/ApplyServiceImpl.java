package jp.co.beattech.workflow.java.app.service;

import org.springframework.stereotype.Service;

import jp.co.beattech.workflow.java.app.domain.Apply;
import jp.co.beattech.workflow.java.app.domain.MstCode;
import jp.co.beattech.workflow.java.app.repository.ApplyRepository;
import jp.co.beattech.workflow.java.app.repository.MstCodeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplyServiceImpl implements ApplyService {

	private final ApplyRepository applyRepository;
	private final MstCodeRepository mstCodeRepository;

	
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