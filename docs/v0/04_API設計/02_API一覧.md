# API一覧

## 概要

Novel Archive v0 で提供するREST APIの一覧を定義する。

---

## API一覧

| No. | 機能            | HTTP Method | URL              |
| --: | ------------- | ----------- | ---------------- |
|   1 | [[03_小説一覧取得API]] | GET         | /api/novels      |
|   2 | [[04_小説詳細取得API]] | GET         | /api/novels/{id} |
|   3 | [[05_小説登録API]]   | POST        | /api/novels      |
|   4 | [[06_小説更新API]]   | PUT         | /api/novels/{id} |
|   5 | [[07_小説削除API]]   | DELETE      | /api/novels/{id} |

---

## API構成

```text
Client
    │
    ▼
Controller
    │
    ▼
Service
    │
    ▼
Dao(Doma)
    │
    ▼
Oracle Database
```
