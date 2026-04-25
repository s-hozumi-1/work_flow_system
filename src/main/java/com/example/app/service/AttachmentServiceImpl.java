package com.example.app.service;

import org.springframework.stereotype.Service;

import com.example.app.domain.Attachment;
import com.example.app.repository.AttachmentRepository;

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