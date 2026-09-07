## 1. Docker構成

本システムはDocker Composeを利用し、Frontend、Backend、Oracle Databaseをコンテナとして実行する。

構成は以下とする。

```text
Browser
   │
   │ http://localhost:5173
   ▼
novel-frontend
React + nginx
   │
   │ /api/*
   ▼
novel-backend
Spring Boot
   │
   │ JDBC
   ▼
novel-oracle
Oracle Database 26ai Free
```

Docker Composeでは以下の3サービスを管理する。

| Service | Container | 役割 |
|---|---|---|
| frontend | novel-frontend | ReactのBuild済みファイルをnginxから配信する |
| backend | novel-backend | Spring Boot REST APIを実行する |
| oracle | novel-oracle | Oracle Databaseを実行する |

## 2. ポート構成

| Service | Host Port | Container Port | 用途 |
|---|---:|---:|---|
| frontend | 5173 | 80 | Web画面 |
| backend | 8080 | 8080 | Spring Boot API |
| oracle | - | 1521 | Docker Network内からBackendが接続 |

通常、ブラウザからは以下へアクセスする。

```text
http://localhost:5173
```

FrontendからBackend APIを利用する場合は、nginxのReverse Proxyを経由する。

```text
Browser
   ↓
http://localhost:5173/api/novels
   ↓
nginx
   ↓
http://backend:8080/api/novels
```

## 3. Frontend Container

FrontendはMulti-stage Buildを利用する。

```text
Build Stage
node:22-alpine
   ↓
npm ci
   ↓
npm run build
   ↓
dist生成

Runtime Stage
nginx:alpine
   ↓
distを配置
   ↓
静的ファイル配信
```

Build StageではReactアプリケーションをBuildし、生成された`dist`をRuntime Stageのnginxへコピーする。

Runtime ContainerではNode.jsを実行せず、Build済みの静的ファイルのみをnginxから配信する。

## 4. nginx

Frontend Containerではnginxを利用する。

主な役割は以下とする。

- Reactの静的ファイル配信
- React Routerへの対応
- Backend APIへのReverse Proxy

### 4.1 React Router

通常のファイルとして存在しないURLへアクセスした場合は`index.html`を返す。

```nginx
try_files $uri $uri/ /index.html;
```

これにより、以下のようなURLへブラウザから直接アクセスした場合でもReact Routerで画面を表示できる。

```text
/novels
/novels/1
/novels/1/edit
```

### 4.2 API Reverse Proxy

`/api/`へのリクエストはBackend Containerへ転送する。

```text
/api/*
   ↓
nginx
   ↓
backend:8080
```

Docker Network内ではDocker ComposeのService名`backend`をホスト名として利用する。

## 5. Backend Container

BackendもMulti-stage Buildを利用する。

```text
Build Stage
eclipse-temurin:21-jdk
   ↓
Gradle
   ↓
bootJar

Runtime Stage
eclipse-temurin:21-jre
   ↓
app.jar
   ↓
Spring Boot起動
```

Build StageではGradle Wrapperを利用してSpring Bootの実行Jarを作成する。

Runtime StageではBuild済みJarのみを配置し、以下の形式でアプリケーションを起動する。

```text
java -jar app.jar
```

Backend Containerは8080ポートで待ち受ける。

## 6. Oracle Container

Oracle Databaseには以下のImageを使用する。

```text
container-registry.oracle.com/database/free:latest
```

Applicationからは`FREEPDB1`へ接続する。

```text
jdbc:oracle:thin:@//oracle:1521/FREEPDB1
```

Docker Network内ではService名`oracle`をDatabaseのホスト名として利用する。

## 7. Oracleデータ永続化

Oracle DatabaseのデータはDocker Volumeへ保存する。

```text
oracle-data
    ↓
/opt/oracle/oradata
```

Containerを削除してもVolumeが残っている場合、Databaseデータは保持される。

```text
docker compose down
→ Volume保持

docker compose down -v
→ Volume削除
```

## 8. Database初期化

Oracle Container起動時に以下のStartup Scriptを実行する。

```text
docker/oracle/startup/
└─ 01_initialize_database.sh
```

初期化処理は以下の流れとする。

```text
Oracle起動
   ↓
初期化Script実行
   ↓
DBユーザー存在確認
   ↓
存在する
   └─ 初期化Skip

存在しない
   ↓
DBユーザー作成
   ↓
権限付与
   ↓
NOVELS作成
   ↓
NOVEL_SEQ作成
   ↓
Trigger作成
   ↓
Sample Data登録
```

初期化対象のSQLファイルはBackendのResource配下にあるものをOracle ContainerへRead OnlyでMountして利用する。

```text
backend/src/main/resources/db/
├─ DDL/
│  ├─ Create_NOVELS.sql
│  └─ Create_TRG_NOVELS_UPDATED_AT.sql
└─ DML/
   └─ Insert_Sample_Data.sql
```

## 9. Database初期化判定

初期化ScriptではApplication用DBユーザーが既に存在するかを確認する。

DBユーザーが存在する場合は初期化済みと判断し、テーブル作成やSample Data登録を再実行しない。

```text
DB_USERNAME存在確認
   ↓
存在する
   → Skip

存在しない
   → 初期化実行
```

これにより、Oracle Containerを再起動した場合でも既存データを維持する。

## 10. Oracle Healthcheck

BackendはOracle Databaseが利用可能になってから起動する。

Oracle ServiceではSQL*Plusを利用したHealthcheckを実行する。

```text
Oracle起動
   ↓
Healthcheck
   ↓
SELECT 1 FROM DUAL
   ↓
Healthy
   ↓
Backend起動
```

Docker ComposeではBackendに以下の依存関係を設定する。

```text
backend
   ↓ depends_on
oracle
   ↓
service_healthy
```

これにより、Oracleの起動完了前にBackendがDatabase接続を開始することを防止する。

## 11. Service間通信

各ContainerはDocker Composeが作成するNetwork内でService名を利用して通信する。

```text
frontend
   │
   └─ backend:8080

backend
   │
   └─ oracle:1521
```

Host側の`localhost`ではなく、Container間通信ではDocker ComposeのService名を利用する。

## 12. 起動順序

基本的な起動関係は以下とする。

```text
oracle
   ↓ Healthy
backend
   ↓
frontend
```

FrontendはBackend Serviceに依存し、BackendはOracleのHealthcheck完了に依存する。

Docker Composeによって3つの実行環境をまとめて管理する。