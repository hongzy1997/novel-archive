# 小説更新API

## 概要

指定された小説情報を更新する。

---

## API情報

|項目|内容|
|---|---|
|機能名|小説更新|
|HTTP Method|PUT|
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
  "title": "全職高手（第4回読了）",
  "author": "蝴蝶藍",
  "readingStatus": 2,
  "rating": 10,
  "memo": "お気に入り作品"
}
```

---

## 入力チェック

|項目|条件|
|---|---|
|id|1以上の整数|
|title|必須、200文字以内|
|author|100文字以内|
|readingStatus|必須、0～3|
|rating|1～10、または未設定|
|memo|1000文字以内|

---

## 正常レスポンス

|項目|内容|
|---|---|
|HTTP Status|200 OK|
|Response Body|更新後の小説情報|

---

## レスポンス例

```json
{
  "novelId": 1,
  "title": "全職高手（第4回読了）",
  "author": "蝴蝶藍",
  "readingStatus": 2,
  "rating": 10,
  "memo": "お気に入り作品",
  "createdAt": "2026-07-20T20:00:00",
  "updatedAt": "2026-07-23T22:10:00"
}
```

---

## 処理概要

1. Controllerが更新リクエストを受け付ける。
2. パスパラメータおよびRequestの入力チェックを実施する。
3. Serviceが対象データの存在を確認する。
4. 対象データが存在しない場合はNovelNotFoundExceptionを発生させる。
5. DaoがNOVELSテーブルを更新する。
6. 更新後のデータを取得する。
7. EntityをNovelResponseへ変換する。
8. 更新結果を返却する。

---

## 更新項目

|カラム|設定値|
|---|---|
|TITLE|Request.title|
|AUTHOR|Request.author|
|READING_STATUS|Request.readingStatus|
|RATING|Request.rating|
|MEMO|Request.memo|
|UPDATED_AT|CURRENT_TIMESTAMP|

※ CREATED_ATは更新しない。

---

## SQL概要

### UPDATE

```sql
UPDATE NOVELS
SET
    TITLE = /* title */ '',
    AUTHOR = /* author */ '',
    READING_STATUS = /* readingStatus */ 0,
    RATING = /* rating */ null,
    MEMO = /* memo */ '',
    UPDATED_AT = CURRENT_TIMESTAMP
WHERE
    NOVEL_ID = /* novelId */ 1
```

---

## 対象データが存在しない場合

### HTTP Status

```text
404 Not Found
```

### エラーコード

```text
NOVEL_NOT_FOUND
```

---

## 入力エラー

### HTTP Status

```text
400 Bad Request
```

### エラーコード

```text
VALIDATION_ERROR
```

---

## エラー一覧

|HTTP Status|エラーコード|発生条件|
|---|---|---|
|400 Bad Request|INVALID_PARAMETER|小説IDの形式が不正|
|400 Bad Request|VALIDATION_ERROR|入力チェックエラー|
|404 Not Found|NOVEL_NOT_FOUND|対象データが存在しない|
|500 Internal Server Error|INTERNAL_SERVER_ERROR|DB接続エラーなどのシステムエラー|

---

## 使用クラス

|分類|クラス名|
|---|---|
|Controller|NovelController|
|Service|NovelService|
|Dao|NovelDao|
|Request|UpdateNovelRequest|
|Entity|Novel|
|Response|NovelResponse|
|Exception|NovelNotFoundException|

---

## 設計方針

- 更新対象はパスパラメータの小説IDで特定する。
- 更新前に対象データの存在を確認する。
- 更新日時のみ現在日時へ更新する。
- 登録日時は変更しない。
- 更新成功後は最新データを返却する。
- Entityは直接返却せず、NovelResponseへ変換する。