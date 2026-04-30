package jp.co.beattech.workflow.java.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.beattech.workflow.java.app.domain.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {

}