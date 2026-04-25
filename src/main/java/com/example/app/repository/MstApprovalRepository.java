package com.example.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.domain.MstApproval;

public interface MstApprovalRepository extends JpaRepository<MstApproval, String> {
	
    List<MstApproval> findByLevel(Integer level);
    
}