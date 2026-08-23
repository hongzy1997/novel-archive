#!/bin/bash

# ============================================================
# Novel Archive
# Oracle Database初期化スクリプト
#
# Oracle Database起動時に実行される。
# NOVEL_ARCHIVEユーザーが存在しない場合のみ、
# ユーザー作成・DB定義・初期データ登録を行う。
# ============================================================

set -e


# ============================================================
# 初期化済み確認
# ============================================================

echo "Novel Archive database initialization check started."

USER_EXISTS=$(sqlplus -s / as sysdba <<EOF
SET HEADING OFF
SET FEEDBACK OFF
SET PAGESIZE 0
SET VERIFY OFF
SET ECHO OFF

ALTER SESSION SET CONTAINER = FREEPDB1;

SELECT COUNT(*)
FROM DBA_USERS
WHERE USERNAME = UPPER('${DB_USERNAME}');

EXIT;
EOF
)

# SQL*Plusの出力に含まれる改行・空白を削除する。
USER_EXISTS=$(echo "${USER_EXISTS}" | tr -d '[:space:]')


# ============================================================
# 初期化済みの場合
# ============================================================

if [ "${USER_EXISTS}" = "1" ]; then
    echo "Database is already initialized. Skip initialization."
    exit 0
fi


# ============================================================
# DBユーザー作成
# ============================================================

echo "Initialize Novel Archive database."

sqlplus -s / as sysdba <<EOF
WHENEVER SQLERROR EXIT SQL.SQLCODE

-- FREEPDB1を操作対象とする。
ALTER SESSION SET CONTAINER = FREEPDB1;

-- Novel Archive用DBユーザーを作成する。
CREATE USER ${DB_USERNAME}
IDENTIFIED BY "${DB_PASSWORD}";

-- アプリケーション実行に必要な権限を付与する。
GRANT CREATE SESSION TO ${DB_USERNAME};
GRANT CREATE TABLE TO ${DB_USERNAME};
GRANT CREATE SEQUENCE TO ${DB_USERNAME};
GRANT CREATE TRIGGER TO ${DB_USERNAME};

-- USERS表領域を利用できるようにする。
ALTER USER ${DB_USERNAME}
QUOTA UNLIMITED ON USERS;

EXIT;
EOF


# ============================================================
# DBオブジェクト・初期データ作成
# ============================================================

sqlplus -s "${DB_USERNAME}/${DB_PASSWORD}@//localhost:1521/FREEPDB1" <<EOF
WHENEVER SQLERROR EXIT SQL.SQLCODE

-- SQL内の空行を有効にする。
SET SQLBLANKLINES ON

-- NOVELSテーブル・シーケンスを作成する。
@/opt/oracle/scripts/app/Create_NOVELS.sql

-- 更新日時自動更新用Triggerを作成する。
@/opt/oracle/scripts/app/Create_TRG_NOVELS_UPDATED_AT.sql

-- 動作確認用初期データを登録する。
@/opt/oracle/scripts/app/Insert_Sample_Data.sql

COMMIT;

EXIT;
EOF


# ============================================================
# 完了
# ============================================================

echo "Novel Archive database initialization completed."