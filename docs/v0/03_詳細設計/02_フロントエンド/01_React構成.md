## 1. React構成

FrontendはReactで構築し、画面単位の処理と共通UI部品を分離する。

現在の構成は以下とする。

```text
src/
├─ main.jsx
├─ App.jsx
├─ App.css
├─ index.css
├─ pages/
│  ├─ NovelListPage.jsx
│  ├─ NovelDetailPage.jsx
│  ├─ NovelCreatePage.jsx
│  └─ NovelEditPage.jsx
├─ components/
│  ├─ Header.jsx
│  ├─ Footer.jsx
│  ├─ NovelCard.jsx
│  ├─ NovelForm.jsx
│  └─ StarRating.jsx
└─ i18n/
   └─ messages.js
```

## 2. main.jsx

`main.jsx`はReactアプリケーションのエントリーポイントとする。

主な役割は以下とする。

- ReactアプリケーションをDOMへ描画する
- `StrictMode`を有効化する
- `BrowserRouter`でアプリケーション全体を囲む
- `App`を起動する

構成は以下とする。

```text
index.html
   ↓
main.jsx
   ↓
BrowserRouter
   ↓
App
```

## 3. App.jsx

`App.jsx`はFrontend全体の基本構成を管理する。

主な役割は以下とする。

- Headerの表示
- Footerの表示
- 表示言語の状態管理
- 画面ルーティング

表示言語は`language`として`App`で管理し、各画面へpropsとして渡す。

```text
App
├─ Header
├─ Routes
│  └─ 各Page
└─ Footer
```

## 4. 画面ルーティング

画面遷移には`react-router-dom`を使用する。

現在のRoute定義は以下とする。

| URL | Component | 画面 |
|---|---|---|
| `/` | `Navigate` | `/novels`へリダイレクト |
| `/novels` | `NovelListPage` | 小説一覧画面 |
| `/novels/new` | `NovelCreatePage` | 小説登録画面 |
| `/novels/:novelId` | `NovelDetailPage` | 小説詳細画面 |
| `/novels/:novelId/edit` | `NovelEditPage` | 小説更新画面 |

小説IDはURLの`novelId`から取得する。

```text
/novels/10
        ↓
useParams()
        ↓
novelId = 10
```

## 5. pages

`pages`配下には画面単位のComponentを配置する。

| Component | 役割 |
|---|---|
| NovelListPage | 小説一覧取得、タイトル検索、一覧表示 |
| NovelDetailPage | 小説詳細取得、詳細表示、削除 |
| NovelCreatePage | 入力値管理、小説登録 |
| NovelEditPage | 既存データ取得、入力値管理、小説更新 |

Page Componentでは主に以下を行う。

- 画面固有の状態管理
- API通信
- 画面固有のイベント処理
- 画面遷移
- 共通Componentへのデータ受け渡し

## 6. components

`components`配下には複数画面または画面内で再利用するUI部品を配置する。

| Component | 役割 |
|---|---|
| Header | システム名および表示言語切替を表示する |
| Footer | 共通Footerを表示する |
| NovelCard | 小説一覧の1件分を表示する |
| NovelForm | 小説登録・更新で共通利用する入力フォーム |
| StarRating | 評価を星形式で表示・入力する |

登録画面と更新画面では共通の入力項目を持つため、入力フォームを`NovelForm`として共通化する。

```text
NovelCreatePage
      ↓
   NovelForm

NovelEditPage
      ↓
   NovelForm
```

## 7. i18n

画面表示用メッセージは`i18n/messages.js`で管理する。

対応言語は以下とする。

- 日本語
- 中国語

`App`で管理している`language`を各Pageへ渡し、各Componentでは選択された言語に対応するメッセージを取得する。

```text
App
 │ language
 ├───────────────┐
 ↓               ↓
Header          Page
                 ↓
              Component
```

## 8. 状態管理

現在のFrontendでは外部の状態管理ライブラリを使用せず、Reactの`useState`を利用して各Component内で状態を管理する。

主な状態は以下とする。

```text
App
└─ language

NovelListPage
├─ novels
├─ searchText
├─ loading
└─ error

NovelDetailPage
├─ novel
├─ loading
├─ errorType
└─ deleteError

NovelCreatePage
├─ 入力値
├─ errors
├─ submitError
└─ duplicateError

NovelEditPage
├─ 入力値
├─ initialData
├─ errors
├─ loading
├─ error
├─ submitError
└─ duplicateError
```

## 9. React Hooks

現在の実装では主に以下のHooksを使用する。

| Hook | 用途 |
|---|---|
| `useState` | 入力値、取得データ、表示状態などの管理 |
| `useEffect` | 画面初期表示時のAPI取得 |
| `useParams` | URLから小説IDを取得 |
| `useNavigate` | API処理完了後などの画面遷移 |

一覧画面および詳細・更新画面では、初回表示時に`useEffect`からBackend APIを呼び出す。