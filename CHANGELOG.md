# Changelog

Novel ArchiveのVersionごとの変更内容を記録する。

## V0

小説情報を管理するWebアプリケーションの基本機能と、Frontend・Backend・DatabaseをDockerで一括実行できる環境を構築した。

### Backend

- Spring Boot Backendを構築
- DomaによるOracle Databaseアクセスを実装
- 小説一覧取得APIを実装
- 小説詳細取得APIを実装
- 小説登録APIを実装
- 小説更新APIを実装
- 小説削除APIを実装
- Bean Validationを実装
- 読書状態と評価の独自Validationを実装
- タイトル重複チェックを実装
- Transaction管理を実装
- 共通例外処理を実装
- API・Serviceのテストを作成

### Frontend

- React Frontendを構築
- 小説一覧画面を実装
- タイトル検索を実装
- 小説詳細画面を実装
- 小説登録画面を実装
- 小説更新画面を実装
- 小説削除機能を実装
- 評価の星表示・入力を実装
- 日本語・中国語の表示切替を実装
- React Routerによる画面遷移を実装
- Backend REST APIとの連携を実装

### Database

- Oracle Database 26ai Freeを導入
- NOVELSテーブルを作成
- NOVEL_ID採番用`NOVEL_SEQ`を作成
- UPDATED_AT自動更新用`TRG_NOVELS_UPDATED_AT`を作成
- 読書状態・評価のCHECK制約を設定
- TITLEのUNIQUE制約を設定
- Sample Data登録SQLを作成

### Docker

- BackendのMulti-stage Buildを構築
- FrontendのMulti-stage Buildを構築
- nginxによるReact静的ファイル配信を設定
- nginxからBackend APIへのReverse Proxyを設定
- Oracle Database Containerを構築
- Docker ComposeによるFrontend・Backend・Databaseの一括起動を構築
- Oracle HealthcheckとBackend起動依存関係を設定
- Docker VolumeによるDatabaseデータ永続化に対応
- Oracle初回起動時のDatabase初期化を自動化
- `.env`による環境依存値の管理に対応
- Shell ScriptをLFでCheckoutする`.gitattributes`を設定
- 別PCでFresh CloneからDocker環境を起動できることを確認

### Documentation

- プロジェクト概要を整理
- 要件定義を整理
- 基本設計を整理
- Backend詳細設計を整理
- Frontend詳細設計を整理
- DB設計を整理
- Docker・環境変数設計を整理
- READMEをV0の最終状態へ更新
