-- ============================================================
-- Novel Archive
-- Version     : 1.0
-- File        : V1_0__Insert_Sample_Data.sql
-- Description : 動作確認用サンプルデータの登録
-- ============================================================

INSERT INTO NOVELS (
    NOVEL_ID,
    TITLE,
    AUTHOR,
    READING_STATUS,
    RATING,
    MEMO,
    CREATED_AT,
    UPDATED_AT
) VALUES (
    SEQ_NOVELS.NEXTVAL,
    '全職高手',
    '蝴蝶藍',
    2,
    10,
    '再読予定',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);


INSERT INTO NOVELS (
    NOVEL_ID,
    TITLE,
    AUTHOR,
    READING_STATUS,
    RATING,
    MEMO,
    CREATED_AT,
    UPDATED_AT
) VALUES (
    SEQ_NOVELS.NEXTVAL,
    '長夜余火',
    '愛潜水的烏賊',
    2,
    8,
    '読了',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);


INSERT INTO NOVELS (
    NOVEL_ID,
    TITLE,
    AUTHOR,
    READING_STATUS,
    RATING,
    MEMO,
    CREATED_AT,
    UPDATED_AT
) VALUES (
    SEQ_NOVELS.NEXTVAL,
    '潑刀行',
    NULL,
    1,
    NULL,
    '読書中',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

COMMIT;