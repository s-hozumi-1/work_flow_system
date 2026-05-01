-- 承認ルートテーブルの作成
CREATE TABLE APPROVAL_ROUTE_TBL (
    id INT NOT NULL AUTO_INCREMENT COMMENT '添付ファイルID',
    management_number INT COMMENT '管理番号',
    file_path VARCHAR(100) COMMENT 'ファイルパス',
    name VARCHAR(100) COMMENT 'ファイル名',
    file_type VARCHAR(100) COMMENT 'ファイルタイプ',
    upload_date DATETIME COMMENT 'アップロード日時',
    
    -- 主キー制約    PRIMARY KEY (id)
) COMMENT='承認ルートテーブル';