# Novel Archive（V0）

Novel Archive は、読んだ小説の情報を管理するための個人開発プロジェクトです。

React、Spring Boot、Oracle Database、Dockerを利用したWebアプリケーションを構築し、
要件定義・設計・実装・テスト・実行環境構築までの一連の開発工程を
学習・実践することを目的としています。

---

# システム概要

登録した小説について、以下の操作を行うWebアプリケーションです。

- 小説一覧表示
- 小説詳細表示
- 小説登録
- 小説更新
- 小説削除

---

# システム構成

```text
Browser
    │
    ▼
React
    │ REST API
    ▼
Spring Boot
    │
    ▼
Oracle Database
```

---

# V0の開発目標

- CRUD機能の実装
- ReactとSpring Bootの連携
- Oracle Databaseへのデータ保存・取得
- Dockerによる実行環境の構築
- 他のPCからアクセスできる環境の構築
- Gitを利用したバージョン管理
- 設計・実装・テストを含む開発工程の実践

---

# 使用技術

| 分類           | 内容                      |
| -------------- | ------------------------- |
| フロントエンド | React                     |
| バックエンド   | Spring Boot 4.1.0         |
| 言語           | Java 21                   |
| データベース   | Oracle Database 26ai Free |
| データアクセス | Doma                      |
| ビルド         | Gradle Kotlin DSL         |
| コンテナ       | Docker Desktop            |
| バージョン管理 | Git / GitHub              |
| 開発環境       | Visual Studio Code        |

---

# ディレクトリ構成

```text
novel-archive
├── backend
│   └── Spring Bootアプリケーション
├── frontend
│   └── Reactアプリケーション
├── README.md
└── CHANGELOG.md
```

設計資料はローカル環境の `docs/v0` 配下で管理しています。

---

# 開発工程

```text
要件定義
    ↓
システム設計
    ↓
DB設計
    ↓
API設計
    ↓
実装設計
    ↓
テスト設計
    ↓
実装・テスト
    ↓
実行環境構築
```

---

# 開発状況

現在、Spring Boot、Oracle Database、Domaを利用した
バックエンドのCRUD機能まで実装済みです。

今後、V0の完成に向けて以下を実施します。

- Reactの基本学習
- CRUD画面の実装
- ReactとSpring Bootの連携
- Dockerによる実行環境の構築
- 他のPCからのアクセス確認