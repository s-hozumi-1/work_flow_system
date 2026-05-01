package jp.co.beattech.workflow.java.app.service;

import org.springframework.stereotype.Service;

import jp.co.beattech.workflow.java.app.domain.Attachment;
import jp.co.beattech.workflow.java.app.repository.AttachmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

	private final AttachmentRepository attachmentRepository;

	
	@Override
	public void addAttachment(Attachment attachment) {
		attachmentRepository.save(attachment);
	}

}