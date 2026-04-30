package jp.co.beattech.workflow.java.app.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "applies")
@Data
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "management_number")
    private Integer managementNumber;
    
    @ManyToOne
    @JoinColumn(name = "detail_cd", referencedColumnName = "code")
    private MstCode detail;
    
    @ManyToOne
    @JoinColumn(name = "expense_cd", referencedColumnName = "code")
    private MstCode expense;
    
    @Column(name = "employee_number")
    private Integer employeeNumber;
    
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "used_date")
    private LocalDate usedDate;
    
    @NotNull
    @Column(name = "payment_amount")
    private Long paymentAmount;
    
    @Size(max = 100)
    @Column(name = "usage_purpose")
    private String usagePurpose;
    
    @Column(name = "application_date")
    private LocalDateTime applicationDate  =LocalDateTime.now();
    
    @Size(max = 100)
    @Column(name = "payee_name")
    private String payeeName;
    
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "approval_date")
    private LocalDate approvalDate;
    
    @ManyToOne
    @JoinColumn(name = "application_status", referencedColumnName = "code")
    private MstCode applicationStatus;
    
    @Size(max = 100)
    @Column(name = "note")
    private String note;
    
    @Column(name = "return_reason")
    private String returnReason;
    
    @Column(name = "del_flg")
    private Integer delFlg = 0;


}