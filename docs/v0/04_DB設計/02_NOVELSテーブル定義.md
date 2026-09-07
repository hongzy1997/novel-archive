## 1. テーブル概要

| 項目 | 内容 |
|---|---|
| テーブル名 | NOVELS |
| 論理名 | 小説情報 |
| 用途 | 小説の基本情報、読書状態、評価、メモを管理する |

## 2. カラム定義

| No. | カラム名 | データ型 | NULL | DEFAULT | 内容 |
|---|---|---|---|---|---|
| 1 | NOVEL_ID | NUMBER(10) | 不可 | - | 小説ID |
| 2 | TITLE | VARCHAR2(200 CHAR) | 不可 | - | タイトル |
| 3 | AUTHOR | VARCHAR2(100 CHAR) | 可 | - | 作者名 |
| 4 | READING_STATUS | NUMBER(1) | 不可 | 0 | 読書状態 |
| 5 | RATING | NUMBER(2) | 可 | - | 評価 |
| 6 | MEMO | VARCHAR2(1000 CHAR) | 可 | - | メモ |
| 7 | CREATED_AT | TIMESTAMP | 不可 | CURRENT_TIMESTAMP | 登録日時 |
| 8 | UPDATED_AT | TIMESTAMP | 不可 | CURRENT_TIMESTAMP | 更新日時 |

## 3. 主キー

`NOVEL_ID`を主キーとする。

```text
PK_NOVELS
└─ NOVEL_ID
```

`NOVEL_ID`は`NOVEL_SEQ`を利用して採番する。

## 4. UNIQUE制約

タイトルの重複登録を防止するため、`TITLE`にUNIQUE制約を設定する。

```text
UK_NOVELS_TITLE
└─ TITLE
```

同一タイトルの小説は複数登録できない。

## 5. 読書状態

`READING_STATUS`は以下の値を使用する。

| 値 | 状態 |
|---|---|
| 0 | 未読 |
| 1 | 読書中 |
| 2 | 読了 |
| 3 | 中断 |

以下のCHECK制約を設定する。

```text
CK_NOVELS_READING_STATUS

READING_STATUS IN (0, 1, 2, 3)
```

初期値は`0：未読`とする。

## 6. 評価

`RATING`は1～10の値を使用する。

読書状態との組み合わせは以下とする。

| READING_STATUS | RATING |
|---|---|
| 0：未読 | NULL |
| 1：読書中 | 1～10 |
| 2：読了 | 1～10 |
| 3：中断 | 1～10 |

以下のCHECK制約を設定する。

```sql
CHECK (
    (READING_STATUS = 0 AND RATING IS NULL)
    OR
    (READING_STATUS IN (1, 2, 3) AND RATING BETWEEN 1 AND 10)
)
```

制約名は`CK_NOVELS_RATING`とする。

## 7. 日時項目

### 7.1 CREATED_AT

レコード登録日時を保持する。

初期値として`CURRENT_TIMESTAMP`を使用する。

```text
DEFAULT CURRENT_TIMESTAMP
```

### 7.2 UPDATED_AT

レコード更新日時を保持する。

登録時の初期値は`CURRENT_TIMESTAMP`とする。

更新時は`TRG_NOVELS_UPDATED_AT`によって自動的に現在日時へ更新する。

## 8. 採番用Sequence

小説IDの採番には`NOVEL_SEQ`を使用する。

定義は以下とする。

| 項目 | 設定 |
|---|---|
| Sequence名 | NOVEL_SEQ |
| START WITH | 1 |
| INCREMENT BY | 1 |
| MINVALUE | 1 |
| MAXVALUE | なし |
| CYCLE | なし |
| CACHE | なし |

BackendではDomaのSequence採番機能を利用する。

```text
Novel
  ↓
@SequenceGenerator(sequence = "NOVEL_SEQ")
  ↓
NOVEL_SEQ
  ↓
NOVEL_ID
```

## 9. 更新日時Trigger

更新日時の自動設定には`TRG_NOVELS_UPDATED_AT`を使用する。

```text
UPDATE NOVELS
   ↓
BEFORE UPDATE
   ↓
TRG_NOVELS_UPDATED_AT
   ↓
UPDATED_AT = CURRENT_TIMESTAMP
```

Triggerは各レコードのUPDATE前に実行する。

## 10. Doma Entityとの対応

`NOVELS`テーブルはBackendの`Novel` Entityと対応する。

| NOVELS | Novel |
|---|---|
| NOVEL_ID | novelId |
| TITLE | title |
| AUTHOR | author |
| READING_STATUS | readingStatus |
| RATING | rating |
| MEMO | memo |
| CREATED_AT | createdAt |
| UPDATED_AT | updatedAt |

`NOVEL_ID`はDomaのSequence生成を利用する。

```java
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
@SequenceGenerator(sequence = "NOVEL_SEQ")
```

登録時はDAOの`@Insert(excludeNull = true)`を利用し、null項目をINSERT対象外とする。

これにより、`CREATED_AT`、`UPDATED_AT`など、値を設定しない項目ではDBのDEFAULT値を利用できる。