-- ============================================================
-- Novel Archive
-- Version     : v0
-- File        : Create_NOVELS.sql
-- Description : NOVELSテーブルおよび採番用シーケンスの作成
-- ============================================================

CREATE SEQUENCE NOVEL_SEQ
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NOMAXVALUE
    NOCYCLE
    NOCACHE;


CREATE TABLE NOVELS (
    NOVEL_ID        NUMBER(10)                          NOT NULL,
    TITLE           VARCHAR2(200 CHAR)                  NOT NULL,
    AUTHOR          VARCHAR2(100 CHAR),
    READING_STATUS  NUMBER(1)       DEFAULT 0           NOT NULL,
    RATING          NUMBER(2),
    MEMO            VARCHAR2(1000 CHAR),
    CREATED_AT      TIMESTAMP       DEFAULT CURRENT_TIMESTAMP NOT NULL,
    UPDATED_AT      TIMESTAMP       DEFAULT CURRENT_TIMESTAMP NOT NULL,

    CONSTRAINT PK_NOVELS
        PRIMARY KEY (NOVEL_ID),

    CONSTRAINT UK_NOVELS_TITLE
        UNIQUE (TITLE),

    CONSTRAINT CK_NOVELS_READING_STATUS
        CHECK (READING_STATUS IN (0, 1, 2, 3)),

    CONSTRAINT CK_NOVELS_RATING
        CHECK (
            (READING_STATUS = 0 AND RATING IS NULL)
            OR
            (READING_STATUS IN (1, 2, 3) AND RATING BETWEEN 1 AND 10)
        )
);


COMMENT ON TABLE NOVELS IS
    '小説情報';

COMMENT ON COLUMN NOVELS.NOVEL_ID IS
    '小説ID';

COMMENT ON COLUMN NOVELS.TITLE IS
    'タイトル';

COMMENT ON COLUMN NOVELS.AUTHOR IS
    '作者名';

COMMENT ON COLUMN NOVELS.READING_STATUS IS
    '読書状態（0:未読、1:読書中、2:読了、3:中断）';

COMMENT ON COLUMN NOVELS.RATING IS
    '評価（1～10、未読の場合はNULL）';

COMMENT ON COLUMN NOVELS.MEMO IS
    'メモ';

COMMENT ON COLUMN NOVELS.CREATED_AT IS
    '登録日時';

COMMENT ON COLUMN NOVELS.UPDATED_AT IS
    '更新日時';