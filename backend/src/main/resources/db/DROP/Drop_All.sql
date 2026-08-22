-- ============================================================
-- Novel Archive
-- Version     : V0
-- File        : Drop_All.sql
-- Description : v0で作成したDBオブジェクトの削除
-- ============================================================

DROP TRIGGER TRG_NOVELS_UPDATED_AT;

DROP TABLE NOVELS;

DROP SEQUENCE NOVEL_SEQ;