package com.example.app.service;

import org.springframework.stereotype.Service;

import com.example.app.domain.Attachment;

@Service
public interface AttachmentService {
	
	void addAttachment(Attachment attachment);
	
	
}
