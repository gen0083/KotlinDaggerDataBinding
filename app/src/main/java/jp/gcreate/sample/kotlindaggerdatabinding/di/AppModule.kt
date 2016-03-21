package jp.gcreate.sample.kotlindaggerdatabinding.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Copyright 2016 G-CREATE
 *
 */
@Module
class AppModule(private val context: Context) {
    @Provides
    @Singleton
    @Named("injectedString")
    fun provideInjectedString(): String{
        return "hoge from app module"
    }
}