package com.example.app.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @Column(name = "employee_number")
    private Integer employeeNumber;

    @NotBlank
    @Size(max = 50)
    @Column(name = "login_id")
    private String loginId;

    @NotBlank
    @Column(name = "password")
    private String password;

    @NotBlank
    @Size(max = 50)
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_cd", referencedColumnName = "code")
    private MstCode userType;

    @ManyToOne
    @JoinColumn(name = "department_cd", referencedColumnName = "code")
    private MstCode department;

    @ManyToOne
    @JoinColumn(name = "branch_cd", referencedColumnName = "code")
    private MstCode branch;

    @Column(name = "registered")
    private LocalDateTime registered =LocalDateTime.now();

    @Column(name = "del_flg")
    private Integer delFlg = 0;
}