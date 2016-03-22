# Kotlin + Dagger2 + DataBinding + Orma

OrmaDatabaseの依存性をDagger2を使って注入しようというサンプル。

これをすべてKotlinで作成した場合、エラーが発生しビルドできない。

これは一度プロジェクトをクリーンしてコンパイルし直すと再現する。（コンパイルするタイミングによってエラーメッセージが変わったりコンパイルできたりするが、クリーンした後コンパイルすると必ず発生する）

```error: cannot access NonExistentClass
  class file for error.NonExistentClass not found
  Consult the following stack trace for details.
  com.sun.tools.javac.code.Symbol$CompletionFailure: class file for error.NonExistentClass not found
1 error

:app:compileDebugJavaWithJavac FAILED

FAILURE: Build failed with an exception.
```

スタックトレースは[stacktrace](stacktrace)を参照のこと。

## 環境

+ Android Studio 2.0 Beta 7
+ jdk 1.8.0_73
+ Kotlin 1.0.1

## 確認したこと

+ フルKotlin ＋ Dagger2で依存性の注入はできる（dagger_kotlin_exclude_ormaブランチ）
+ 全てJavaの場合動作する(dagger_javaブランチ)

他のAnnotation Processingによるコード生成を行うクラスをDagger2で注入しようとしても発生するのかどうかは不明。（Ormaの問題なのか切り分けできていない）

## 考えられる原因

`provideOrmaDatabase()`や`@Inject lateinit var orma:OrmaDatabase`をコメントアウトすればコンパイルできることから、Dagger2がkapt上ではAnnotation Processingによって生成されたコードをうまく参照できていないと思われる。

[kaptのIssue KT-10352](https://youtrack.jetbrains.com/issue/KT-10352)の現象に近い気がする。

## 回避方法

+ Dagger2で依存性注入するところはJavaで書く
+ Annotation Processingで生成するコードをDagger2でセットアップしない
+ kaptのバージョンアップを待つ

他にいい方法あったら教えて下さい。
