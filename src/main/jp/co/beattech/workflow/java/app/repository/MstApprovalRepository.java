package jp.co.beattech.workflow.java.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.beattech.workflow.java.app.domain.MstApproval;

public interface MstApprovalRepository extends JpaRepository<MstApproval, Integer> {
	
    List<MstApproval> findAll();
}