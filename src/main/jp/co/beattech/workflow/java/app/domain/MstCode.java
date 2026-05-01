package jp.co.beattech.workflow.java.app.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "mst_code")
@Data
public class MstCode {

    
    private String type;

    @Id
    private String code;

    
    private String category;

    private String name;
}