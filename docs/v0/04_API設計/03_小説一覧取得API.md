# 小説一覧取得API

## 概要

登録されている小説情報を一覧で取得する。

---

## API情報

|項目|内容|
|---|---|
|機能名|小説一覧取得|
|HTTP Method|GET|
|URL|`/api/novels`|
|Content-Type|`application/json`|

---

## リクエスト

### パスパラメータ

なし。

### クエリパラメータ

v0では使用しない。

### リクエストボディ

なし。

---

## レスポンス

### 正常終了

|項目|内容|
|---|---|
|HTTP Status|200 OK|
|Response Body|小説情報の配列|

---

## レスポンス項目

|項目|データ型|NULL|内容|
|---|---|:---:|---|
|novelId|number|不可|小説ID|
|title|string|不可|タイトル|
|author|string|可|作者名|
|readingStatus|number|不可|読書状態|
|rating|number|可|評価|
|memo|string|可|メモ|
|createdAt|string|不可|登録日時|
|updatedAt|string|不可|更新日時|

---

## レスポンス例

```json
[
  {
    "novelId": 1,
    "title": "全職高手",
    "author": "蝴蝶藍",
    "readingStatus": 2,
    "rating": 10,
    "memo": "再読予定",
    "createdAt": "2026-07-23T21:30:00",
    "updatedAt": "2026-07-23T21:30:00"
  },
  {
    "novelId": 2,
    "title": "長夜余火",
    "author": "愛潜水的烏賊",
    "readingStatus": 2,
    "rating": 8,
    "memo": "読了",
    "createdAt": "2026-07-23T21:35:00",
    "updatedAt": "2026-07-23T21:35:00"
  }
]
```

---

## データが存在しない場合

対象データが0件の場合は、空配列を返却する。

### HTTP Status

```text
200 OK
```

### レスポンス例

```json
[]
```

---

## 処理概要

1. Controllerが一覧取得リクエストを受け付ける。
2. Serviceが小説一覧取得処理を実行する。
3. DaoがNOVELSテーブルから小説情報を取得する。
4. EntityをNovelResponseへ変換する。
5. 小説情報の配列を返却する。

---

## 取得条件

NOVELSテーブルに登録されている全データを取得する。

---

## 並び順

以下の順序で取得する。

```text
NOVEL_ID ASC
```

---

## SQL概要

```sql
SELECT
    NOVEL_ID,
    TITLE,
    AUTHOR,
    READING_STATUS,
    RATING,
    MEMO,
    CREATED_AT,
    UPDATED_AT
FROM
    NOVELS
ORDER BY
    NOVEL_ID ASC
```

---

## エラー

|HTTP Status|エラーコード|発生条件|
|---|---|---|
|500 Internal Server Error|INTERNAL_SERVER_ERROR|DB接続エラーなどのシステムエラーが発生した場合|

---

## 使用クラス

|分類|クラス名|
|---|---|
|Controller|NovelController|
|Service|NovelService|
|Dao|NovelDao|
|Entity|Novel|
|Response|NovelResponse|

---

## 設計方針

- v0では検索条件およびページングを使用しない。
- データが0件の場合は404ではなく空配列を返却する。
- Entityは直接返却せず、NovelResponseへ変換する。
- 一覧の並び順はNOVEL_IDの昇順とする。