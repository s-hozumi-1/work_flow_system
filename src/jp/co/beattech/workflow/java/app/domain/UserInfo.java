package jp.co.beattech.workflow.java.app.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "view_user_info") 
@Data  
public class UserInfo {
	
	@Id
	private Integer employeeNumber;
	
	private String loginId;
	
	private String password;
	
	private String name;
	
	private String userCd;
	
	private String userType;
	
	private String departmentCd;
	
	private String department;
	
	private String branchCd;
	
	private String branch;
	
	private Integer level;
	
}










