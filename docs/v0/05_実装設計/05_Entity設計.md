# 05_Entity設計

## 概要

本書では、Entityの役割および実装方針について説明する。

Entityは、データベーステーブルと対応するデータ保持用クラスとして利用する。

---

# 役割

Entityでは以下の役割を担当する。

- テーブルとのマッピング
- データ保持
- Daoとのデータ受け渡し

業務ロジックは実装しない。

---

# 実装方針

EntityにはDoma Entityを利用する。

Getter / Setterなどの定型コードはLombokを利用する。

---

# 使用するアノテーション

| アノテーション | 用途 |
|---------------|------|
| @Entity | Entityを定義する |
| @Table | テーブル名を定義する |
| @Id | 主キーを定義する |
| @Data | Getter / Setter等を自動生成する |

---

# 実装ルール

- 業務ロジックを実装しない。
- データ保持のみを目的とする。
- テーブルと対応するクラス名とする。
- Dao以外から直接更新しない。

---

# 命名規則

| 種類 | 命名例 |
|------|--------|
| Entity | Novel |
| フィールド | novelId |
| フィールド | title |
| フィールド | author |

---

# 実装例

```java
@Entity
@Data
@Table(name = "NOVELS")
public class Novel {

    @Id
    private Long novelId;

    private String title;

    private String author;

}
```

---

# 設計理由

- テーブルとクラスを対応させることで、可読性を向上させる。
- Lombokを利用し、定型コードを削減する。
- Entityの責務をデータ保持のみに限定し、保守性を向上させる。