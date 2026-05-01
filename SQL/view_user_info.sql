- ユーザー情報統合ビューの作成
CREATE OR REPLACE VIEW view_user_info AS
SELECT
    u.employee_number,
    u.login_id,
    u.password,
    u.name,
    u.user_cd,
    c1.name AS user_type,      -- ユーザ種別名 (MST_CD_TBLから取得)
    u.department_cd,
    c2.name AS department,     -- 所属グループ名 (MST_CD_TBLから取得)
    u.branch_cd,
    c3.name AS branch_name,    -- 支店名 (MST_CD_TBLから取得、物理名を修正)
    a.level                    -- 承認レベル (MST_APPROVAL_TBLから取得)
FROM
    USER_TBL u
-- 承認レベルの結合 (設定がない場合を考慮しLEFT JOIN)
LEFT JOIN
    MST_APPROVAL_TBL a 
    ON u.employee_number = a.employee_number
-- ユーザ種別名の結合
LEFT JOIN
    MST_CD_TBL c1 
    ON u.user_cd = c1.code 
-- 所属グループ名の結合
LEFT JOIN
    MST_CD_TBL c2 
    ON u.department_cd = c2.code 
-- 支店名の結合
LEFT JOIN
    MST_CD_TBL c3 
    ON u.branch_cd = c3.code;