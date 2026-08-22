SELECT
    CASE
        WHEN COUNT(*) > 0 THEN 1
        ELSE 0
    END
FROM
    NOVELS
WHERE
    TITLE = /* title */''
AND NOVEL_ID <> /* novelId */0