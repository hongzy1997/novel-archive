## 1. API連携概要

Frontendでは`fetch`を利用してBackend APIと通信する。

API通信は各Page Componentから実行し、取得結果やエラー状態をPage内で管理する。

```text
Page Component
   ↓ fetch
Backend API
   ↓ Response
Page Component
   ↓
状態更新
   ↓
画面再描画
```

## 2. API一覧

Frontendから利用するAPIは以下とする。

| 処理 | HTTP Method | URL | 利用画面 |
|---|---|---|---|
| 小説一覧取得 | GET | `/api/novels` | NovelListPage |
| 小説詳細取得 | GET | `/api/novels/{novelId}` | NovelDetailPage / NovelEditPage |
| 小説登録 | POST | `/api/novels` | NovelCreatePage |
| 小説更新 | PUT | `/api/novels/{novelId}` | NovelEditPage |
| 小説削除 | DELETE | `/api/novels/{novelId}` | NovelDetailPage |

## 3. 小説一覧取得

`NovelListPage`の初期表示時に小説一覧取得APIを呼び出す。

```text
NovelListPage表示
   ↓
useEffect
   ↓
GET /api/novels
   ↓
成功
   └─ novelsへ設定
```

取得中は`loading`をtrueとし、処理完了後にfalseへ戻す。

取得に失敗した場合は`error`を設定し、一覧の代わりにエラーメッセージを表示する。

タイトル検索ではAPIを再実行せず、取得済みの`novels`をFrontend側で絞り込む。

## 4. 小説詳細取得

`NovelDetailPage`ではURLから`novelId`を取得し、詳細取得APIを呼び出す。

```text
useParams()
   ↓
novelId
   ↓
GET /api/novels/{novelId}
   ↓
novelへ設定
```

404が返却された場合は、小説が存在しない状態として表示する。

それ以外の取得エラーは一般的な取得エラーとして扱う。

## 5. 小説登録

`NovelCreatePage`で登録操作が行われた場合、入力内容をRequest Bodyとして登録APIへ送信する。

```text
入力内容
   ↓
Frontend Validation
   ↓ OK
POST /api/novels
   ↓
Backend
```

送信するJSONは以下の形式とする。

```json
{
  "title": "小説タイトル",
  "author": "作者",
  "readingStatus": 2,
  "rating": 9,
  "memo": "メモ"
}
```

登録成功時はBackendから返却された`novelId`を利用し、登録した小説の詳細画面へ遷移する。

```text
POST成功
   ↓
NovelResponse
   ↓
novelId取得
   ↓
/novels/{novelId}
```

## 6. 小説更新

`NovelEditPage`では画面初期表示時に詳細取得APIを実行し、現在の小説情報を入力フォームへ設定する。

更新操作時はFrontend Validation後、更新APIを実行する。

```text
入力内容
   ↓
Frontend Validation
   ↓ OK
PUT /api/novels/{novelId}
   ↓
更新成功
   ↓
/novels/{novelId}
```

更新成功後は対象小説の詳細画面へ遷移する。

## 7. 小説削除

`NovelDetailPage`で削除操作が行われた場合、確認処理後に削除APIを実行する。

```text
削除操作
   ↓
確認
   ↓ OK
DELETE /api/novels/{novelId}
   ↓
削除成功
   ↓
/novels
```

削除成功後は小説一覧画面へ遷移する。

削除に失敗した場合は`deleteError`を設定し、詳細画面上にエラーを表示する。

## 8. Request Header

登録・更新APIではJSONを送信するため、以下のHeaderを設定する。

```http
Content-Type: application/json
```

GETおよびDELETEではRequest Bodyを送信しない。

## 9. APIエラー処理

APIレスポンスが正常でない場合は、処理内容に応じてエラー状態を設定する。

| HTTP Status | Frontendでの扱い |
|---|---|
| 404 | 詳細取得時は対象小説が存在しない状態として表示する |
| 409 | 登録・更新時はタイトル重複エラーとして扱う |
| その他のエラー | 一般エラーとして扱う |

### 9.1 タイトル重複

登録・更新時に409 Conflictが返却された場合は、`duplicateError`をtrueに設定してタイトル重複エラーを表示する。

### 9.2 その他のエラー

409以外の登録・更新エラーや通信失敗は`submitError`として扱う。

一覧・詳細取得、削除については各Pageで管理しているエラー状態へ設定する。

Backendでは400 Bad Request時に項目別Validationエラーを返却するが、現在のFrontendではそのレスポンス内容を個別には展開せず、一般的な登録・更新エラーとして扱う。

## 10. Frontend Validationとの関係

登録・更新時はBackend APIを呼び出す前にFrontendでも入力チェックを行う。

```text
ユーザー入力
   ↓
Frontendで入力制御・Validation
   ↓ OK
Backend API
   ↓
Backend Validation
```

Frontendでは以下によって入力内容を制御する。

- タイトル必須チェック
- 未読以外の場合の評価必須チェック
- `maxLength`によるタイトル、作者、メモの文字数制限
- 選択肢による読書状態の制限
- `StarRating`による1～10の評価入力

Backendでは`NovelRequest`に対するValidationを実行し、APIとして不正なデータを受け付けない。
