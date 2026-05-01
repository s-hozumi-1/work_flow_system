CREATE TABLE USER_TBL (
    employee_number INT NOT NULL COMMENT '社員番号',
    login_id VARCHAR(50) NOT NULL COMMENT 'ログインID',
    password VARCHAR(100) NOT NULL COMMENT 'パスワード',
    name VARCHAR(50) NOT NULL COMMENT 'ユーザ名',
    user_cd INT NOT NULL COMMENT 'ユーザ種別CD',
    department_cd INT NOT NULL COMMENT '所属グループCD',
    branch_cd INT NOT NULL COMMENT '支店CD',
    registered DATETIME NOT NULL COMMENT '登録日時',
    del_flg INT NOT NULL COMMENT '削除フラグ',
    
    -- 主キー制約
    PRIMARY KEY (employee_number),
    
    -- ユニーク制約
    UNIQUE (login_id)
) COMMENT='ユーザーテーブル';