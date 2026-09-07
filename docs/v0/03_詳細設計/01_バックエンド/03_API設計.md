## 1. API一覧

| No. | HTTP Method | URL | 処理 | 正常時Status |
|---|---|---|---|---|
| 1 | GET | `/api/novels` | 小説一覧取得 | 200 |
| 2 | GET | `/api/novels/{novelId}` | 小説詳細取得 | 200 |
| 3 | POST | `/api/novels` | 小説登録 | 200 |
| 4 | PUT | `/api/novels/{novelId}` | 小説更新 | 200 |
| 5 | DELETE | `/api/novels/{novelId}` | 小説削除 | 200 |

## 2. 共通レスポンス項目

小説情報を返却するAPIでは`NovelResponse`を使用する。

| 項目 | 型 | 内容 |
|---|---|---|
| novelId | Long | 小説ID |
| title | String | タイトル |
| author | String | 作者 |
| readingStatus | Integer | 読書状態 |
| rating | Integer | 評価 |
| memo | String | メモ |

`author`、`rating`、`memo`はnullとなる場合がある。

## 3. 小説一覧取得

### 3.1 リクエスト

```http
GET /api/novels
```

Request Bodyは使用しない。

### 3.2 処理

1. `NovelController`から`NovelService.findAll()`を呼び出す。
2. `NovelDao`から小説一覧を取得する。
3. 取得したEntityを`NovelResponse`へ変換する。
4. 小説一覧を返却する。

### 3.3 レスポンス

```json
[
  {
    "novelId": 1,
    "title": "全职高手",
    "author": "蝴蝶蓝",
    "readingStatus": 2,
    "rating": 10,
    "memo": "..."
  }
]
```

登録データが存在しない場合は空配列を返却する。

```json
[]
```

## 4. 小説詳細取得

### 4.1 リクエスト

```http
GET /api/novels/{novelId}
```

| 項目 | 種別 | 型 | 内容 |
|---|---|---|---|
| novelId | Path Parameter | Long | 取得対象の小説ID |

### 4.2 処理

1. 指定された`novelId`で小説情報を検索する。
2. 対象が存在する場合、`NovelResponse`へ変換して返却する。
3. 対象が存在しない場合、`NovelNotFoundException`を発生させる。

### 4.3 正常レスポンス

```json
{
  "novelId": 31,
  "title": "诡秘之主",
  "author": "爱潜水的乌贼",
  "readingStatus": 2,
  "rating": 9,
  "memo": "基本读完。"
}
```

### 4.4 異常レスポンス

対象の小説が存在しない場合：

```text
404 Not Found
```

```json
{
  "message": "対象の小説が存在しません。id=9999"
}
```

## 5. 小説登録

### 5.1 リクエスト

```http
POST /api/novels
Content-Type: application/json
```

```json
{
  "title": "小説タイトル",
  "author": "作者",
  "readingStatus": 2,
  "rating": 9,
  "memo": "メモ"
}
```

### 5.2 Request項目

| 項目 | 型 | 必須 | 制約 |
|---|---|---|---|
| title | String | ○ | 空文字不可、200文字以内 |
| author | String | - | 100文字以内 |
| readingStatus | Integer | ○ | 0～3 |
| rating | Integer | 条件付き | 1～10 |
| memo | String | - | 1000文字以内 |

読書状態と評価の組み合わせは以下とする。

| readingStatus | 状態 | rating |
|---|---|---|
| 0 | 未読 | null |
| 1 | 読書中 | 必須 |
| 2 | 読了 | 必須 |
| 3 | 中断 | 必須 |

### 5.3 処理

1. Request Bodyを`NovelRequest`として受け取る。
2. Bean Validationを実行する。
3. タイトルの前後空白を除去する。
4. 同一タイトルの小説が存在しないことを確認する。
5. `Novel` Entityを生成する。
6. Databaseへ登録する。
7. 登録結果を`NovelResponse`へ変換して返却する。

作者およびメモは前後空白を除去し、空文字となった場合はnullとして登録する。

### 5.4 正常レスポンス

登録した小説情報を返却する。

```json
{
  "novelId": 54,
  "title": "小説タイトル",
  "author": "作者",
  "readingStatus": 2,
  "rating": 9,
  "memo": "メモ"
}
```

### 5.5 異常レスポンス

Validationエラーの場合：

```text
400 Bad Request
```

例：

```json
{
  "title": "タイトルは必須です。"
}
```

タイトルが既に登録されている場合：

```text
409 Conflict
```

```json
{
  "message": "同じタイトルの小説が既に登録されています。title=小説タイトル"
}
```

## 6. 小説更新

### 6.1 リクエスト

```http
PUT /api/novels/{novelId}
Content-Type: application/json
```

```json
{
  "title": "小説タイトル",
  "author": "作者",
  "readingStatus": 1,
  "rating": 8,
  "memo": "更新後メモ"
}
```

Request項目およびValidationは小説登録APIと同様とする。

### 6.2 処理

1. Bean Validationを実行する。
2. `novelId`に対応する小説を取得する。
3. 対象が存在しない場合は`NovelNotFoundException`を発生させる。
4. タイトルの前後空白を除去する。
5. 自分自身を除く他の小説とタイトルが重複していないことを確認する。
6. Entityの内容を更新する。
7. Databaseを更新する。
8. 更新結果を`NovelResponse`へ変換して返却する。

### 6.3 異常レスポンス

| Status | 条件 |
|---|---|
| 400 Bad Request | Request内容がValidationエラー |
| 404 Not Found | 指定した小説が存在しない |
| 409 Conflict | 他の小説とタイトルが重複する |

## 7. 小説削除

### 7.1 リクエスト

```http
DELETE /api/novels/{novelId}
```

| 項目 | 種別 | 型 | 内容 |
|---|---|---|---|
| novelId | Path Parameter | Long | 削除対象の小説ID |

### 7.2 処理

1. `novelId`に対応する小説を取得する。
2. 対象が存在しない場合は`NovelNotFoundException`を発生させる。
3. 対象小説をDatabaseから削除する。

正常終了時のResponse Bodyは返却しない。

### 7.3 異常レスポンス

対象の小説が存在しない場合：

```text
404 Not Found
```

## 8. Validationエラー

登録・更新APIでは`NovelRequest`に対してBean Validationを実行する。

Validationエラー時は以下の形式でフィールド名とエラーメッセージを返却する。

```json
{
  "title": "タイトルは必須です。",
  "rating": "未読の場合は評価できません。未読以外の場合は評価が必須です。"
}
```

主なValidationは以下とする。

| 項目 | チェック |
|---|---|
| title | 必須、200文字以内 |
| author | 100文字以内 |
| readingStatus | 必須、0～3 |
| rating | 1～10 |
| readingStatus / rating | 未読の場合は評価なし、未読以外の場合は評価必須 |
| memo | 1000文字以内 |

## 9. エラーStatus

| HTTP Status | 条件 |
|---|---|
| 400 Bad Request | Validationエラー |
| 404 Not Found | 指定した小説が存在しない |
| 409 Conflict | 同一タイトルの小説が存在する |