# 小説削除API

## 概要

指定された小説情報を削除する。

---

## API情報

|項目|内容|
|---|---|
|機能名|小説削除|
|HTTP Method|DELETE|
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
DELETE /api/novels/1
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
|Response Body|なし|

---

## 処理概要

1. Controllerが削除リクエストを受け付ける。
2. パスパラメータの入力チェックを実施する。
3. Serviceが対象データの存在を確認する。
4. 対象データが存在しない場合はNovelNotFoundExceptionを発生させる。
5. DaoがNOVELSテーブルから対象データを削除する。
6. 正常終了レスポンスを返却する。

---

## 削除条件

以下の条件でNOVELSテーブルから削除する。

```text
NOVEL_ID = 指定された小説ID
```

---

## SQL概要

```sql
DELETE FROM
    NOVELS
WHERE
    NOVEL_ID = /* novelId */ 1
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
  "timestamp": "2026-07-23T22:30:00"
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
  "timestamp": "2026-07-23T22:30:00"
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
|Exception|NovelNotFoundException|

---

## トランザクション

削除処理はトランザクション内で実行する。

削除処理中にエラーが発生した場合は、
トランザクションをロールバックする。

---

## 設計方針

- 削除対象はパスパラメータの小説IDで特定する。
- 削除前に対象データの存在を確認する。
- v0では論理削除ではなく物理削除とする。
- 削除成功時はレスポンスボディを返却しない。
- 対象データが存在しない場合は404を返却する。