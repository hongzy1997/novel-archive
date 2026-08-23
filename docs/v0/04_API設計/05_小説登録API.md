# 小説登録API

## 概要

新しい小説情報を登録する。

---

## API情報

|項目|内容|
|---|---|
|機能名|小説登録|
|HTTP Method|POST|
|URL|`/api/novels`|
|Content-Type|`application/json`|

---

## リクエスト

### パスパラメータ

なし。

### クエリパラメータ

なし。

### リクエストボディ

|項目|データ型|必須|内容|
|---|---|:---:|---|
|title|string|必須|タイトル|
|author|string|任意|作者名|
|readingStatus|number|必須|読書状態|
|rating|number|任意|評価|
|memo|string|任意|メモ|

---

## リクエスト例

```json
{
  "title": "全職高手",
  "author": "蝴蝶藍",
  "readingStatus": 2,
  "rating": 10,
  "memo": "再読予定"
}
```

---

## 入力チェック

|項目|条件|
|---|---|
|title|必須、200文字以内|
|author|100文字以内|
|readingStatus|必須、0～3|
|rating|1～10、または未設定|
|memo|1000文字以内|

---

## 正常レスポンス

|項目|内容|
|---|---|
|HTTP Status|201 Created|
|Response Body|登録した小説情報|

---

## レスポンス例

```json
{
  "novelId": 3,
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

1. Controllerが登録リクエストを受け付ける。
2. Requestの入力チェックを実施する。
3. Serviceが登録処理を実行する。
4. DaoがSEQ_NOVELSから小説IDを採番する。
5. NOVELSテーブルへ登録する。
6. 登録したデータを取得する。
7. EntityをNovelResponseへ変換する。
8. 登録結果を返却する。

---

## 登録項目

|カラム|設定値|
|---|---|
|NOVEL_ID|SEQ_NOVELS.NEXTVAL|
|TITLE|Request.title|
|AUTHOR|Request.author|
|READING_STATUS|Request.readingStatus|
|RATING|Request.rating|
|MEMO|Request.memo|
|CREATED_AT|CURRENT_TIMESTAMP|
|UPDATED_AT|CURRENT_TIMESTAMP|

---

## SQL概要

### INSERT

```sql
INSERT INTO NOVELS (
    NOVEL_ID,
    TITLE,
    AUTHOR,
    READING_STATUS,
    RATING,
    MEMO,
    CREATED_AT,
    UPDATED_AT
)
VALUES (
    SEQ_NOVELS.NEXTVAL,
    /* title */ '',
    /* author */ '',
    /* readingStatus */ 0,
    /* rating */ null,
    /* memo */ '',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
)
```

---

## 入力エラー

入力チェックエラーが発生した場合は、
400 Bad Requestを返却する。

### HTTP Status

```text
400 Bad Request
```

### エラーコード

```text
VALIDATION_ERROR
```

---

## レスポンス例

```json
{
  "status": 400,
  "errorCode": "VALIDATION_ERROR",
  "message": "入力内容に誤りがあります。",
  "path": "/api/novels",
  "timestamp": "2026-07-23T21:30:00"
}
```

---

## エラー一覧

|HTTP Status|エラーコード|発生条件|
|---|---|---|
|400 Bad Request|VALIDATION_ERROR|入力チェックエラー|
|500 Internal Server Error|INTERNAL_SERVER_ERROR|DB接続エラーなどのシステムエラー|

---

## 使用クラス

|分類|クラス名|
|---|---|
|Controller|NovelController|
|Service|NovelService|
|Dao|NovelDao|
|Request|CreateNovelRequest|
|Entity|Novel|
|Response|NovelResponse|

---

## 設計方針

- 小説IDはOracle Databaseのシーケンスで採番する。
- 登録日時および更新日時はDBサーバー時刻を使用する。
- 登録成功時は201 Createdを返却する。
- 登録後の最新データを取得してレスポンスとして返却する。
- Entityは直接返却せず、NovelResponseへ変換する。