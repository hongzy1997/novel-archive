-- ============================================================
-- Novel Archive
-- Version     : V0
-- File        : Create_TRG_NOVELS_UPDATED_AT.sql
-- Description : NOVELSテーブルの更新日時を自動更新するトリガーの作成
-- ============================================================

CREATE OR REPLACE TRIGGER TRG_NOVELS_UPDATED_AT
BEFORE UPDATE ON NOVELS
FOR EACH ROW
BEGIN
    :NEW.UPDATED_AT := CURRENT_TIMESTAMP;
END;
/