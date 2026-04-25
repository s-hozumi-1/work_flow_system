package com.example.app.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "view_application_info")
@Data
public class ApplicationInfo {

	@Id
	private Integer managementNumber;

	private String applicationName;
	
	private String applicationDetailCd;

	private String applicationDetail;

	private String expenseTypeCd;
	
	private String expenseType;

	private LocalDate usedDate;

	private Integer paymentAmount;

	private String usagePurpose;

	private String payeeName;

	private LocalDateTime applicationDate;

	private LocalDate approvalDate;

	private String note;
	
	private String filePath;

	private String fileName;

	private String applicationStatusCd;
	
	private String applicationStatus;

}