# Calculator App

Quarkusフレームワークを使用したシンプルな電卓Webアプリケーション

## 機能

- 基本的な四則演算（足し算、引き算、掛け算、割り算）
- クイック入力用のテンキーパッド
- レスポンシブデザイン
- エラーハンドリング（ゼロ除算、無効な入力など）

## 技術スタック

- **フレームワーク**: Quarkus 3.15.1
- **Java**: 17
- **テンプレートエンジン**: Qute
- **REST**: RESTEasy Reactive
- **ビルドツール**: Maven

## 前提条件

- JDK 17以上
- Maven 3.8以上

## セットアップ

### 開発モード

```bash
./mvnw quarkus:dev
```

アプリケーションは http://localhost:8080/calculator でアクセスできます。

### パッケージング

```bash
./mvnw package
```

`quarkus-run.jar`が`target/quarkus-app/`に生成されます。

### アプリケーション実行

```bash
java -jar target/quarkus-app/quarkus-run.jar
```

## ネイティブビルド

### ネイティブ実行可能ファイルの作成

```bash
./mvnw package -Pnative
```

GraalVMがインストールされていない場合、コンテナビルドを使用：

```bash
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

### ネイティブ実行可能ファイルの実行

```bash
./target/calculator-app-1.0.0-SNAPSHOT-runner
```

## テスト

```bash
./mvnw test
```

## プロジェクト構成

```
src/
├── main/
│   ├── java/
│   │   └── com/example/
│   │       └── CalculatorController.java  # メインコントローラー
│   └── resources/
│       ├── templates/
│       │   └── calculator.html            # Quteテンプレート
│       └── META-INF/
│           └── resources/
│               └── css/
│                   └── calculator.css      # スタイルシート
└── test/
    └── java/
        └── com/example/
            └── CalculatorControllerTest.java
```

## 関連リンク

- [Quarkus公式サイト](https://quarkus.io/)
- [Quarkus ガイド](https://quarkus.io/guides/)
