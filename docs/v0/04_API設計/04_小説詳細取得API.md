# 小説詳細取得API

## 概要

指定された小説IDに該当する小説情報を取得する。

---

## API情報

|項目|内容|
|---|---|
|機能名|小説詳細取得|
|HTTP Method|GET|
|URL|`/api/novels/{id}`|
|Content-Type|`application/json`|

---

## リクエスト

### パスパラメータ

|項目|データ型|必須|内容|
|---|---|:---:|---|
|id|number|必須|小説ID|

### クエリパラメータ

なし。

### リクエストボディ

なし。

---

## リクエスト例

```text
GET /api/novels/1
```

---

## 入力チェック

|項目|条件|
|---|---|
|id|1以上の整数|

---

## 正常レスポンス

|項目|内容|
|---|---|
|HTTP Status|200 OK|
|Response Body|指定された小説情報|

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
{
  "novelId": 1,
  "title": "全職高手",
  "author": "蝴蝶藍",
  "readingStatus": 2,
  "rating": 10,
  "memo": "再読予定",
  "createdAt": "2026-07-23T21:30:00",
  "updatedAt": "2026-07-23T21:30:00"
}
```

---

## 処理概要

1. Controllerが小説IDを受け取る。
2. Serviceが小説詳細取得処理を実行する。
3. DaoがNOVELSテーブルから対象データを取得する。
4. 対象データが存在しない場合はNovelNotFoundExceptionを発生させる。
5. EntityをNovelResponseへ変換する。
6. 小説情報を返却する。

---

## 取得条件

以下の条件でNOVELSテーブルを検索する。

```text
NOVEL_ID = 指定された小説ID
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
WHERE
    NOVEL_ID = /* novelId */1
```

---

## 対象データが存在しない場合

指定された小説IDに該当するデータが存在しない場合は、
404 Not Foundを返却する。

### HTTP Status

```text
404 Not Found
```

### エラーコード

```text
NOVEL_NOT_FOUND
```

### レスポンス例

```json
{
  "status": 404,
  "errorCode": "NOVEL_NOT_FOUND",
  "message": "指定された小説が存在しません。",
  "path": "/api/novels/999",
  "timestamp": "2026-07-23T21:30:00"
}
```

---

## パラメータ形式が不正な場合

小説IDに数値以外が指定された場合、または1未満の値が指定された場合は、
400 Bad Requestを返却する。

### HTTP Status

```text
400 Bad Request
```

### エラーコード

```text
INVALID_PARAMETER
```

### レスポンス例

```json
{
  "status": 400,
  "errorCode": "INVALID_PARAMETER",
  "message": "小説IDの形式が正しくありません。",
  "path": "/api/novels/abc",
  "timestamp": "2026-07-23T21:30:00"
}
```

---

## エラー一覧

|HTTP Status|エラーコード|発生条件|
|---|---|---|
|400 Bad Request|INVALID_PARAMETER|小説IDの形式が不正な場合|
|404 Not Found|NOVEL_NOT_FOUND|対象データが存在しない場合|
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
|Exception|NovelNotFoundException|

---

## 設計方針

- 小説IDはパスパラメータで受け取る。
- 対象データが存在しない場合は404を返却する。
- Entityは直接返却せず、NovelResponseへ変換する。
- 小説IDは1以上の整数のみ許可する。