package jp.co.beattech.workflow.java.app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "approval_routes")
@Data
public class ApprovalRoute {

    @Id
    @Column(name = "management_number")
    private Integer managementNumber;
    
    @ManyToOne
    @JoinColumn(name = "detail_cd", referencedColumnName = "code")
    private MstCode detail;
    
    @ManyToOne
    @JoinColumn(name = "approval_cd", referencedColumnName = "code")
    private MstCode approval;
    
    @ManyToOne
    @JoinColumn(name = "approver_no1" , referencedColumnName = "employeeNumber")
    private MstApproval approverNo1;
    
    @ManyToOne
    @JoinColumn(name = "approver_no2" , referencedColumnName = "employeeNumber")
    private MstApproval approverNo2;
    
    @ManyToOne
    @JoinColumn(name = "approver_no3" , referencedColumnName = "employeeNumber")
    private MstApproval approverNo3;
    


}