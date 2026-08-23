# 02_Controller設計

## 概要

本書では、Controllerレイヤーの役割および実装方針について説明する。

Controllerは、クライアントからのHTTPリクエストを受け付け、Serviceを呼び出し、処理結果をResponseとして返却する役割を持つ。

---

# 役割

Controllerでは以下の処理を担当する。

- HTTPリクエストの受付
- Requestオブジェクトへの変換
- 入力値のバリデーション
- Serviceの呼び出し
- Responseの返却
- HTTPステータスコードの設定

業務ロジックおよびデータベースアクセスは実装しない。

---

# 実装方針

ControllerはREST APIとして実装する。

HTTP通信に関する処理のみを担当し、業務ロジックはServiceへ委譲する。

---

# 使用するアノテーション

| アノテーション | 用途 |
|---------------|------|
| @RestController | REST APIを定義する |
| @RequestMapping | 共通URLを定義する |
| @GetMapping | データ取得API |
| @PostMapping | データ登録API |
| @PutMapping | データ更新API |
| @DeleteMapping | データ削除API |
| @RequestBody | Request Bodyを受け取る |
| @PathVariable | URLパラメータを取得する |
| @Valid | 入力チェックを実施する |

---

# 実装ルール

- ControllerはHTTP通信のみを担当する。
- 業務ロジックはServiceへ委譲する。
- Daoを直接呼び出さない。
- SQLを実行しない。
- Entityを直接返却しない。
- Responseクラスを返却する。

---

# 命名規則

| 種類 | 命名例 |
|------|--------|
| Controller | NovelController |
| URL | /api/novels |
| 一覧取得 | getNovels |
| 詳細取得 | getNovel |
| 登録 | createNovel |
| 更新 | updateNovel |
| 削除 | deleteNovel |

---

# 実装例

```java
@RestController
@RequestMapping("/api/novels")
@RequiredArgsConstructor
public class NovelController {

    private final NovelService novelService;

}
```

---

# 設計理由

- Controllerの責務をHTTP通信のみに限定することで、業務ロジックとの分離を実現する。
- Serviceへ処理を委譲することで、保守性および再利用性を向上させる。
- Responseクラスを利用することで、API仕様と内部実装を分離する。
- Spring Boot標準のREST API実装方式を採用し、可読性および保守性を向上させる。