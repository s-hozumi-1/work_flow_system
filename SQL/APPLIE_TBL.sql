-- 申請テーブルの作成
CREATE TABLE APPLIE_TBL (
    management_number INT NOT NULL AUTO_INCREMENT COMMENT '管理番号',
    detail_cd VARCHAR(10) NOT NULL COMMENT '申請内容種別CD',
    expense_cd VARCHAR(10) NOT NULL COMMENT '経費種別CD',
    employee_number INT NOT NULL COMMENT '社員番号',
    used_date DATE NOT NULL COMMENT '使用日',
    payment_amount BIGINT NOT NULL COMMENT '出金額',
    usage_purpose VARCHAR(100) COMMENT '使途・目的',
    application_date DATETIME NOT NULL COMMENT '申請日時',
    payee_name VARCHAR(100) NOT NULL COMMENT '支払先',
    approval_date DATE COMMENT '承認期日',
    application_status VARCHAR(10) COMMENT '申請状況',
    note VARCHAR(100) COMMENT '備考',
    return_reason VARCHAR(100) COMMENT '差し戻し理由',
    del_flg INT NOT NULL COMMENT '削除フラグ',
    
    -- 主キー制約
    PRIMARY KEY (management_number)
) COMMENT='申請テーブル';