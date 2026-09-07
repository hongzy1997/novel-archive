# Novel Archive

Novel Archiveは、読んだ小説や気になる小説を管理するための個人向けWebアプリケーションです。

小説の読書状態、評価、メモなどを記録し、一覧・詳細画面から継続的に管理できます。
また、個人開発を通してFrontend、Backend、Database、Dockerを利用したWebアプリケーション開発を学習することを目的としています。

**Current Version: V0**

## 主な機能

- 小説一覧表示
- タイトル検索
- 小説詳細表示
- 小説登録
- 小説編集
- 小説削除
- 読書状態管理
- 1～10の評価管理
- 日本語・中国語の表示切替

## システム構成

```text
Browser
   │
   ▼
Frontend
React + nginx
   │
   │ REST API
   ▼
Backend
Spring Boot
   │
   │ Doma
   ▼
Database
Oracle Database 26ai Free
```

Frontend、Backend、Oracle DatabaseはDocker Composeで実行します。

## 使用技術

| 分類            | 技術                        |
| --------------- | --------------------------- |
| Frontend        | React / Vite                |
| Web Server      | nginx                       |
| Backend         | Java 21 / Spring Boot 4.1.1 |
| Database Access | Doma                        |
| Database        | Oracle Database 26ai Free   |
| Build           | Gradle Kotlin DSL           |
| Infrastructure  | Docker / Docker Compose     |
| Version Control | Git / GitHub                |

## ディレクトリ構成

```text
novel-archive/
├─ backend/
│  └─ Spring Bootアプリケーション
├─ frontend/
│  └─ Reactアプリケーション
├─ docker/
│  └─ Oracle Database初期化スクリプト
├─ docs/
│  └─ バージョン別設計資料
├─ compose.yml
├─ .env.example
├─ README.md
└─ CHANGELOG.md
```

V0の設計資料は`docs/v0/`配下で管理しています。

## 実行方法

### 1. 前提

以下を利用できる環境を用意します。

- Git
- Docker Desktop

### 2. Repository取得

```bash
git clone https://github.com/hongzy1997/novel-archive.git
cd novel-archive
```

### 3. 環境変数ファイル作成

`.env.example`をコピーして`.env`を作成します。

Windows:

```powershell
copy .env.example .env
```

macOS / Linux:

```bash
cp .env.example .env
```

作成した`.env`にローカル環境で使用するパスワードを設定します。

### 4. 起動

```bash
docker compose up -d --build
```

初回起動時はOracle DatabaseのApplication用ユーザー、DBオブジェクト、Sample Dataを自動作成します。

### 5. アクセス

```text
http://localhost:5173
```

Frontendのnginxが`/api/*`をBackendへReverse Proxyします。

### 6. 停止

```bash
docker compose down
```

Oracle DatabaseのデータはDocker Volumeに保存されるため、通常の停止・再起動では保持されます。

Databaseを含めて初期化する場合はVolumeを削除します。

```bash
docker compose down -v
```

## ドキュメント

設計資料はVersionごとに管理します。

```text
docs/
└─ v0/
   ├─ 00_プロジェクト概要
   ├─ 01_要件定義
   ├─ 02_基本設計
   ├─ 03_詳細設計
   ├─ 04_DB設計
   └─ 05_インフラ・実行環境
```

変更履歴は`CHANGELOG.md`で管理します。
