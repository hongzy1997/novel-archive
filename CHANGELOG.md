
このファイルでは、Novel Archive の変更履歴を管理します。

---

## V0

### Added

#### プロジェクト

- プロジェクトを作成
- READMEを作成
- CHANGELOGを作成

#### バックエンド

- Spring Bootプロジェクトを作成
- Domaを導入
- 小説管理CRUD REST APIを実装
- バリデーションを実装
- トランザクション管理を実装
- 例外の共通処理を実装

#### フロントエンド

- Reactプロジェクトを作成
- 小説一覧画面を実装
- 小説詳細画面を実装
- 小説登録画面を実装
- 小説更新画面を実装
- 小説削除機能を実装
- REST APIとの連携を実装

#### データベース

- Oracle Databaseを導入
- NOVELSテーブルを作成
- 採番用シーケンスを作成
- 更新日時自動更新用トリガーを作成
- 初期データ登録SQLを作成

#### Docker

- BackendのDocker実行環境を構築
- FrontendのDocker実行環境を構築
- Oracle DatabaseのDocker実行環境を構築
- Docker Composeによる一括起動環境を構築
- Oracle Databaseの初期化処理を自動化
- Docker VolumeによるDBデータ永続化に対応
- nginxによるFrontend配信およびBackend APIへのリバースプロキシを設定
- `.env`による環境依存設定の管理に対応

#### テスト

- APIテストを作成
- 単体テストを作成
- Frontend・Backend・Databaseの連携動作を確認
- Docker環境での起動・再起動およびデータ永続化を確認

#### ドキュメント

- 要件定義を作成
- システム設計を作成
- DB設計を作成
- API設計を作成
- 画面設計を作成
- テスト計画を作成