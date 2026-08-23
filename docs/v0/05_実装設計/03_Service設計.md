# 03_Service設計

## 概要

本書では、Serviceレイヤーの役割および実装方針について説明する。

Serviceは、アプリケーションの業務ロジックを実装するレイヤーであり、Controllerから呼び出され、必要に応じてDaoを利用してデータベースへアクセスする。

---

# 役割

Serviceでは以下の処理を担当する。

- 業務ロジックの実装
- Daoの呼び出し
- データ加工
- トランザクション管理
- ControllerとDaoの仲介

---

# 実装方針

ServiceにはSpring FrameworkのService機能を利用する。

データの登録・更新・削除を行う処理では、必要に応じてトランザクションを管理する。

---

# 使用するアノテーション

| アノテーション | 用途 |
|---------------|------|
| @Service | Serviceクラスとして定義する |
| @Transactional | トランザクションを管理する |
| @RequiredArgsConstructor | コンストラクタインジェクションを利用する |

---

# 実装ルール

- Controllerからのみ呼び出す。
- Daoを利用してデータを取得・更新する。
- SQLを実装しない。
- HTTP通信を実装しない。
- 業務ロジックはServiceへ集約する。

---

# 命名規則

| 種類 | 命名例 |
|------|--------|
| Service | NovelService |
| メソッド | getNovel |
| メソッド | createNovel |
| メソッド | updateNovel |
| メソッド | deleteNovel |

---

# 実装例

```java
@Service
@RequiredArgsConstructor
public class NovelService {

    private final NovelDao novelDao;

}
```

---

# 設計理由

- 業務ロジックをServiceへ集約し、責務を明確にする。
- ControllerとDaoの仲介を行うことで、レイヤー構造を維持する。
- トランザクション管理を集約し、データの整合性を確保する。