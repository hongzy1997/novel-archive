## 1. API一覧

FrontendからBackendへ、小説情報を操作するためのREST APIを提供する。

基本パスは以下とする。

```text
/api/novels
```

| No. | HTTP Method | URL | 概要 |
|---|---|---|---|
| 1 | GET | `/api/novels` | 小説一覧を取得する |
| 2 | GET | `/api/novels/{novelId}` | 指定した小説を取得する |
| 3 | POST | `/api/novels` | 小説を登録する |
| 4 | PUT | `/api/novels/{novelId}` | 指定した小説を更新する |
| 5 | DELETE | `/api/novels/{novelId}` | 指定した小説を削除する |

## 2. 小説情報

APIでは主に以下の小説情報を扱う。

| 項目            | 内容   |
| ------------- | ---- |
| novelId       | 小説ID |
| title         | タイトル |
| author        | 作者   |
| readingStatus | 読書状態 |
| rating        | 評価   |
| memo          | メモ   |

## 3. 登録・更新データ

小説登録および更新では以下の情報をFrontendから送信する。

| 項目 | 必須 | 内容 |
|---|---|---|
| title | ○ | 小説タイトル |
| author | - | 作者 |
| readingStatus | ○ | 読書状態 |
| rating | 条件付き | 評価 |
| memo | - | メモ |

評価は読書状態が「未読」の場合は設定しない。

「未読」以外の場合は1～10の評価を設定する。

## 4. API利用画面

| API | 主な利用画面 |
|---|---|
| 小説一覧取得 | 小説一覧画面 |
| 小説取得 | 小説詳細画面、小説更新画面 |
| 小説登録 | 小説登録画面 |
| 小説更新 | 小説更新画面 |
| 小説削除 | 小説詳細画面 |