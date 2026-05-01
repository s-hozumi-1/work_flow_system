package jp.co.beattech.workflow.java.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.beattech.workflow.java.app.domain.MstCode;
import jp.co.beattech.workflow.java.app.repository.MstCodeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MstCodeServiceImpl implements MstCodeService {
	
	private final MstCodeRepository mstCodeRepository;

	@Override
	public List<MstCode> getCodesByType(String type) {
		return mstCodeRepository.findByType(type);
	}
	@Override
    public String findNameByCode(String code) {
        MstCode mstCode = mstCodeRepository.findByCode(code);
        return mstCode != null ? mstCode.getName() : "";
    }

}
