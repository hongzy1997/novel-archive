# Novel Archive

Novel Archive は、小説に関する情報や読書記録を管理するための
個人向けWebアプリケーションです。

読んだ作品や読みたい作品を記録し、
読書状態、評価、メモなどの情報を整理・蓄積することで、
自身の読書履歴を継続的に管理・参照できることを目的としています。

また、Webアプリケーション開発における
要件定義、設計、実装、テスト、実行環境構築までの
一連の開発工程を実践する個人開発プロジェクトとして、
継続的に機能追加・改善を行います。

---

# システム構成

```text
Browser
    │
    ▼
Frontend
React + nginx
    │
    │ /api
    ▼
Backend
Spring Boot
    │
    ▼
Oracle Database
```

Frontend、Backend、Oracle Databaseは、
Docker Composeを利用して実行します。


---

# 使用技術

| 分類 | 内容 |
| --- | --- |
| フロントエンド | React |
| Webサーバー | nginx |
| バックエンド | Spring Boot |
| 言語 | Java |
| データベース | Oracle Database |
| データアクセス | Doma |
| ビルド | Gradle Kotlin DSL |
| 実行環境 | Docker / Docker Compose |
| バージョン管理 | Git / GitHub |


---

# ディレクトリ構成

```text
novel-archive
├── backend
│   └── Spring Bootアプリケーション
├── frontend
│   └── Reactアプリケーション
├── docker
│   └── Oracle Database初期化スクリプト
├── docs
│   └── 設計資料
├── compose.yml
├── .env.example
├── README.md
└── CHANGELOG.md
```

設計資料は `docs` 配下で管理しています。


---

# 実行方法

## 1. 環境変数ファイルの作成

`.env.example` をコピーして `.env` を作成します。

```powershell
copy .env.example .env
```

`.env` にローカル環境で使用するパスワードを設定します。


## 2. アプリケーションの起動

```powershell
docker compose up -d --build
```

Docker Composeにより、以下の環境が起動します。

- Frontend
- Backend
- Oracle Database


## 3. アプリケーションへのアクセス

ブラウザから以下へアクセスします。

```text
http://localhost:5173
```


## 4. アプリケーションの停止

```powershell
docker compose down
```

Oracle DatabaseのデータはDocker Volumeに保存されるため、
通常の停止・再起動では保持されます。