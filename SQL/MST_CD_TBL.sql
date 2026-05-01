-- コードマスタテーブルの作成
CREATE TABLE MST_CD_TBL (
    type VARCHAR(10) NOT NULL COMMENT 'コードタイプ',
    category VARCHAR(100) NOT NULL COMMENT '分類',
    code VARCHAR(10) NOT NULL COMMENT 'コード',
    name VARCHAR(100) NOT NULL COMMENT 'コード名',
    
    -- 主キー制約
    PRIMARY KEY (code)
) COMMENT='コードマスタテーブル';