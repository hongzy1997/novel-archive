-- ============================================================
-- Novel Archive
-- Version     : 1.0
-- File        : V1_0__Drop_All.sql
-- Description : v1.0で作成したDBオブジェクトの削除
-- ============================================================

DROP TABLE NOVELS CASCADE CONSTRAINTS PURGE;

DROP SEQUENCE SEQ_NOVELS;