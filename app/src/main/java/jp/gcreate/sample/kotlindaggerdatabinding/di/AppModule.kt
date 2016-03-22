package jp.gcreate.sample.kotlindaggerdatabinding.di

import android.content.Context
import dagger.Module
import dagger.Provides
import jp.gcreate.sample.kotlindaggerdatabinding.databases.OrmaDatabase
import javax.inject.Named
import javax.inject.Singleton

/**
 * Copyright 2016 G-CREATE

 */
@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    @Named("injectedString")
    fun provideInjectedString(): String {
        return "hoge from app module"
    }

    @Provides
    @Singleton
    fun provideOrmaDatabase(context: Context): OrmaDatabase {
        return OrmaDatabase.builder(context).build()
    }
}