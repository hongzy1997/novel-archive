# Component構成

## 目的

Novel Archive V0のフロントエンドにおけるComponent構成を定義する。

画面設計で定義した各画面を、
React Componentとしてどのように分割・構成するかを整理する。

---

## 全体構成

想定Component構成：

```text
App
├── Header
│   └── LanguageSwitcher
│
├── Router
│   ├── NovelListPage
│   │   ├── SearchArea
│   │   └── NovelCard
│   │       └── RatingStars
│   │
│   ├── NovelDetailPage
│   │   └── RatingStars
│   │
│   ├── NovelCreatePage
│   │   └── NovelForm
│   │       └── RatingStars
│   │
│   └── NovelEditPage
│       └── NovelForm
│           └── RatingStars
│
└── Footer
```

Component名は実装時に必要に応じて調整する。

---

## App

アプリケーション全体のルートComponent。

主な役割：

- 共通レイアウトの構築
- Header / Footerの配置
- React Routerの設定
- 各画面Componentの切替

イメージ：

```text
App
 ├── Header
 ├── Router
 │    └── 各画面
 └── Footer
```

---

## Header

全画面共通のHeaderを表示する。

表示内容：

- Novel Archive
- システム名
- 言語切替

```text
Header
    └── LanguageSwitcher
```

---

## LanguageSwitcher

日本語 / 中文の表示切替を行う。

```text
日本語
  ↕
中文
```

言語切替によって変更する対象：

- 画面タイトル
- Label
- Button
- Message
- 読書状態表示

APIから取得した以下の業務データは変更しない。

```text
title
author
memo
```

言語状態は複数画面で利用するため、
実装時に共通管理方法を検討する。

---

## Router

React Routerを使用してURLと画面Componentを対応付ける。

```text
/novels
→ NovelListPage

/novels/new
→ NovelCreatePage

/novels/:novelId
→ NovelDetailPage

/novels/:novelId/edit
→ NovelEditPage
```

```text
URL
 ↓
React Router
 ↓
Page Component
```

---

# NovelListPage

小説一覧画面を担当するComponent。

主な役割：

- 小説一覧APIの呼出
- 一覧データの保持
- タイトル検索
- Loading / Error / Empty表示
- NovelCardの生成
- 登録画面への遷移

主なState：

```text
novels
→ API取得データ

searchText
→ 検索文字

loading
→ 一覧取得中

error
→ API Error
```

データフロー：

```text
GET /api/novels
      ↓
    novels
      ↓
   filter()
      ↓
filteredNovels
      ↓
    map()
      ↓
 NovelCard
```

---

## SearchArea

一覧画面の検索入力部分を担当する。

主な役割：

- 検索文字入力
- 検索条件変更

V0ではタイトル部分一致検索のみ対応する。

実装規模が小さい場合は、
NovelListPage内に直接実装してもよい。

---

## NovelCard

一覧画面で1件の小説情報を表示する。

表示内容：

- タイトル
- 著者
- 評価
- 読書状態

Props例：

```text
novelId
title
author
rating
readingStatus
```

選択時：

```text
NovelCard
    ↓
novelId
    ↓
/novels/{novelId}
    ↓
NovelDetailPage
```

---

# NovelDetailPage

小説詳細画面を担当する。

主な役割：

- URLから `novelId` を取得
- 小説詳細APIの呼出
- 詳細情報表示
- 更新画面への遷移
- 削除処理
- 一覧画面への遷移

主なState：

```text
novel
loading
error
deleting
```

データ取得：

```text
URL
 ↓
novelId
 ↓
GET /api/novels/{novelId}
 ↓
novel
 ↓
詳細表示
```

---

# NovelCreatePage

小説登録画面を担当する。

主な役割：

- NovelFormの表示
- 登録用初期値設定
- POST API実行
- 登録成功後の画面遷移

初期値：

```text
title
→ ""

author
→ ""

readingStatus
→ 0（未読）

rating
→ 未選択

memo
→ ""
```

登録成功後：

```text
POST
 ↓
novelId取得
 ↓
/novels/{novelId}
```

---

# NovelEditPage

小説更新画面を担当する。

主な役割：

- URLから `novelId` を取得
- 現在データ取得
- NovelFormへ初期値を渡す
- PUT API実行
- 更新成功後の画面遷移

```text
URL
 ↓
novelId
 ↓
GET /api/novels/{novelId}
 ↓
現在値
 ↓
NovelForm
 ↓
編集
 ↓
PUT /api/novels/{novelId}
```

---

# NovelForm

登録画面と更新画面で共通利用する入力Form。

共通項目：

```text
title
author
readingStatus
rating
memo
```

利用：

```text
NovelCreatePage
      ↓
   NovelForm

NovelEditPage
      ↓
   NovelForm
```

共通化することで、
登録画面と更新画面で同じ入力UI・Validationを利用する。

主な役割：

- 入力値管理
- Frontend Validation
- 読書状態選択
- 評価選択
- メモ入力
- Submit Event

登録・更新で異なる処理は、
Parent Component側から渡す。

---

# RatingStars

評価を星形式で表示・入力する共通Component。

利用箇所：

```text
NovelListPage
→ 表示専用

NovelDetailPage
→ 表示専用

NovelForm
→ 入力可能
```

API値：

```text
1 ～ 10
```

UI：

```text
0.5 ～ 5.0
```

変換：

```text
表示星数 = rating / 2
```

入力時：

```text
星数 × 2
→ API rating
```

表示用 / 入力用の違いはPropsなどで切り替える想定とする。

---

## Stateの配置方針

Stateは、そのStateを必要とするComponentに配置する。

基本：

```text
そのComponentだけで使用
→ Component内でState管理
```

例：

```text
NovelListPage
→ searchText

NovelForm
→ title / author / rating など
```

複数Componentで共通利用するStateは、
必要に応じて上位Componentへ配置する。

例：

```text
language
→ 複数画面で使用
→ 共通管理
```

詳細な共通State管理方法は、
実装時に必要に応じて決定する。

---

## Propsの利用

Parent ComponentからChild Componentへ必要なデータをPropsで渡す。

例：

```text
NovelListPage
    ↓ Props
NovelCard
```

```text
NovelCreatePage
    ↓ Props
NovelForm
```

Component間で必要以上に多くのデータを渡さず、
各Componentの役割に必要な値のみ渡す。

---

## API通信の配置

API通信は基本的にPage Component側で行う。

```text
Page Component
    ↓
API Request
    ↓
State更新
    ↓
Child ComponentへProps
```

例：

```text
NovelListPage
    ↓
GET /api/novels
    ↓
novels
    ↓
NovelCard
```

表示専用Componentに直接API通信を持たせない方針とする。

---

## Component設計方針

- Page Componentは画面単位の処理を担当する
- 共通UIは再利用可能なComponentとして分割する
- API通信はPage Component側で管理する
- Formは登録・更新で共通化する
- RatingStarsは表示・入力で共通利用する
- Stateは必要な範囲に配置する
- Componentを細かく分割しすぎず、役割が明確な単位で分ける

---

## ポイント

```text
Page Component
→ API / State / 画面制御

Common Component
→ 共通UI / 再利用

Props
→ ParentからChildへデータを渡す

State
→ 必要なComponentで管理
```

Novel Archiveでは、

```text
画面設計
    ↓
Page Component
    ↓
共通Component
    ↓
React実装
```

という形で実装する。