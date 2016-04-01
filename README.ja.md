# Kotlin + Dagger2 + DataBinding + Orma

OrmaDatabaseの依存性をDagger2を使って注入しようというサンプル。

これをすべてKotlinで作成した場合、エラーが発生しビルドできない。

これは一度プロジェクトをクリーンしてコンパイルし直すと必ず再現する。（コンパイルするタイミングによってエラーメッセージが変わったりコンパイルできたりするが、クリーンした後コンパイルすると必ず発生する）

```
error: cannot access NonExistentClass
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
+ Annotation Processingによってコード生成されたクラスをDagger2で扱うとコンパイルできない（DataBindingとOrmaで確認）
+ それは@Providesもしくは@Injectのどちらかにでも指定されていれば生じる
+ 既にOrmaやDataBindingのコードが生成済みであればDagger2で扱ってもコンパイルできる

## 考えられる原因

`provideOrmaDatabase()`や`@Inject lateinit var orma:OrmaDatabase`をコメントアウトすればコンパイルできることから、Dagger2がkapt上ではAnnotation Processingによって生成されたコードをうまく参照できていないと思われる。

[kaptのIssue KT-10352](https://youtrack.jetbrains.com/issue/KT-10352)の現象に近い気がする。

この現象は、Dagger2でAnnotation Processingによって生成されるクラスを参照するようになると生じる。

Javaオンリーの環境では再現しないことから、kaptではコード生成による依存関係をうまく裁くことができていないものと思われる。
このサンプルの状態で言えば、Dagger2のコード生成をするのにOrmaのコード生成が必要な状態になってしまっているせいで起こる。
先にOrmaのコード生成をすべて処理した後にDagger2のコード生成を行ってくれれば問題がないのだが、その順番をうまく解決できないのだと思う。
kaptによるコード生成のプロセス中では、生成するコード同士がお互いを参照できないのか、もしくは生成する順番を制御できないのかもしれない。

## 回避方法

+ Dagger2で依存性注入するところはJavaで書く
+ Annotation Processingで生成するコードをDagger2でセットアップしない
+ kaptのバージョンアップを待つ

ちなみにとても面倒くさい上に非現実的な回避策もある。

+ Dagger2でAnnotation Processingを使うクラスの依存性を注入している部分(@Provides、@Injectしている部分)をすべてコメントアウトする
+ 一旦コンパイルしてAnnotation Processingによるコード生成を行う
+ その後コメントアウトした部分を元に戻す
+ 再度コンパイルしてDagger2のコードを生成する

この順番でコンパイルを行うとコンパイルできる。ただし、一度でもクリーンしてしまうとまたコンパイルできなくなる。

他にいい方法あったら教えて下さい。
