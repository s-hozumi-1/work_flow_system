-- 承認マスタテーブルの作成
CREATE TABLE MST_APPROVAL_TBL (
    employee_number INT NOT NULL COMMENT '社員番号',
    level INT NOT NULL COMMENT '承認レベル',
    
    -- 主キー制約
    PRIMARY KEY (employee_number)
) COMMENT='承認マスタテーブル';