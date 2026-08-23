# 04_Dao設計

## 概要

本書では、Daoレイヤーの役割および実装方針について説明する。

Daoは、Domaを利用してデータベースへアクセスするレイヤーであり、SQLの実行およびデータ取得・更新を担当する。

---

# 役割

Daoでは以下の処理を担当する。

- データベースアクセス
- SQLの実行
- Entityの取得
- Entityの登録・更新・削除

業務ロジックは実装しない。

---

# 実装方針

DaoにはDomaを利用する。

SQLはSQLファイルとして管理し、DaoにはSQLを直接記述しない。

---

# 使用するアノテーション

| アノテーション | 用途 |
|---------------|------|
| @Dao | Daoインターフェースを定義する |
| @ConfigAutowireable | Spring Bootと連携する |

---

# 実装ルール

- SQLはDaoのみで実行する。
- SQLはSQLファイルへ記述する。
- Controllerから直接呼び出さない。
- Service経由で利用する。
- 業務ロジックを実装しない。

---

# 命名規則

| 種類 | 命名例 |
|------|--------|
| Dao | NovelDao |
| SQL | selectAll.sql |
| SQL | selectById.sql |
| SQL | insert.sql |
| SQL | update.sql |
| SQL | delete.sql |

---

# 実装例

```java
@Dao
@ConfigAutowireable
public interface NovelDao {

    List<Novel> selectAll();

    Optional<Novel> selectById(Long novelId);

    int insert(Novel novel);

    int update(Novel novel);

    int delete(Novel novel);
}
```

---

# 設計理由

- SQLとJavaコードを分離し、可読性を向上させる。
- Domaを利用することで、SQLを明示的に管理する。
- Daoの責務をデータアクセスのみに限定し、保守性を向上させる。