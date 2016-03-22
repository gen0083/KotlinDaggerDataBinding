# Kotlin + Dagger2 + DataBinding + Orma sample

This is my sample project to use Dagger2 + Kotlin.

But this could not compiled when generated code provide with Dagger2 in Kotlin.

This replicate when recompile after clean project.

Error message:

```
error: cannot access NonExistentClass
  class file for error.NonExistentClass not found
  Consult the following stack trace for details.
  com.sun.tools.javac.code.Symbol$CompletionFailure: class file for error.NonExistentClass not found
1 error

:app:compileDebugJavaWithJavac FAILED

FAILURE: Build failed with an exception.
```

See [stacktrace](stacktrace) by `./gradlew :app:compileDebugJavaWithJavac --stacktrace`.

## Environment

+ Android Studio 2.0 Beta 7
+ jdk 1.8.0_73
+ Kotlin 1.0.1

## Check

+ I can inject with Dagger2 in Kotlin (checkout branch dagger_kotlin_exclude_orma)
+ I can inject generated code with Dagger2 in Java (checkout branch dagger_java)

## What causes

I seems to be a bug in kapt.
Because i can compile after comment out `provideOrmaDatabase()` and `@Inject lateinit var orma:OrmaDatabase`.
Dagger2 can not resolve generated code by annotation processing in kapt?

I think this is similar to [issue KT-10352](https://youtrack.jetbrains.com/issue/KT-10352).

## Avoid

+ Write in Java to inject Dagger2
+ Avoid to inject generated code with Dagger2 in Kotlin
+ Wait to version up kapt

Any idea?