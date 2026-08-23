## テーブル概要

| 項目 | 内容 |
|---|---|
| テーブル名 | NOVELS |
| 論理名 | 小説情報 |
| 主キー | NOVEL_ID |
| 概要 | 小説の基本情報および読書状況を管理する |

---

## カラム一覧

| No. | カラム名 | データ型 | NULL | 初期値 | 概要 |
|---:|---|---|:---:|---|---|
| 1 | NOVEL_ID | NUMBER(10) | 不可 | SEQ_NOVELS.NEXTVAL | 小説ID |
| 2 | TITLE | VARCHAR2(200) | 不可 | なし | タイトル |
| 3 | AUTHOR | VARCHAR2(100) | 可 | なし | 作者名 |
| 4 | READING_STATUS | NUMBER(1) | 不可 | 0 | 読書状態 |
| 5 | RATING | NUMBER(2) | 可 | なし | 評価 |
| 6 | MEMO | VARCHAR2(1000) | 可 | なし | メモ |
| 7 | CREATED_AT | TIMESTAMP | 不可 | CURRENT_TIMESTAMP | 登録日時 |
| 8 | UPDATED_AT | TIMESTAMP | 不可 | CURRENT_TIMESTAMP | 更新日時 |

---

## 制約

### 主キー

| 制約名 | 対象カラム |
|---|---|
| PK_NOVELS | NOVEL_ID |

### チェック制約

| 制約名 | 対象カラム | 条件 |
|---|---|---|
| CK_NOVELS_READING_STATUS | READING_STATUS | 0～3 |
| CK_NOVELS_RATING | RATING | 1～10 または NULL |

---

## 読書状態

| コード | 名称 |
|---:|---|
| 0 | 未読 |
| 1 | 読書中 |
| 2 | 読了 |
| 3 | 中断 |

---

## 採番方式

NOVEL_ID は Oracle Database のシーケンスを使用して採番する。

| 項目 | 内容 |
|---|---|
| シーケンス名 | SEQ_NOVELS |
| 開始値 | 1 |
| 増分値 | 1 |

---

## 日時管理

### 登録時

- CREATED_AT に現在日時を設定する。
- UPDATED_AT に現在日時を設定する。

### 更新時

- CREATED_AT は変更しない。
- UPDATED_AT を現在日時に更新する。

---

## インデックス

なし

---

## 関連ファイル

| 種別   | パス                                                         |
| ---- | ---------------------------------------------------------- |
| DDL  | `backend/src/main/resources/db/DDL/Create_NOVELS.sql`      |
| DML  | `backend/src/main/resources/db/DML/Insert_Sample_Data.sql` |
| DROP | `backend/src/main/resources/db/DROP/Drop_All.sql`          |

---

## 設計理由

- 主キーには Oracle Database のシーケンスを使用し、一意なIDを安全に採番する。
- 読書状態は数値コードで管理し、処理を簡潔にする。
- 登録日時・更新日時を保持することで、データの変更履歴を管理しやすくする。