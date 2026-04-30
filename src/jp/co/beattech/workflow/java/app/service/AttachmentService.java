package jp.co.beattech.workflow.java.app.service;

import org.springframework.stereotype.Service;

import jp.co.beattech.workflow.java.app.domain.Attachment;

@Service
public interface AttachmentService {
	
	void addAttachment(Attachment attachment);
	
	
}
