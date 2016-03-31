package jp.gcreate.sample.kotlindaggerdatabinding.di

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides
import jp.gcreate.sample.kotlindaggerdatabinding.R
import jp.gcreate.sample.kotlindaggerdatabinding.databases.TestData
import jp.gcreate.sample.kotlindaggerdatabinding.databinding.ActivityMainBinding
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
    @Named("contextString")
    fun provideContextString(context: Context): String{
        return context.javaClass.name
    }

    @Provides
    fun provideTestData(): TestData {
        return TestData(10, "from Dagger2")
    }

//    @Provides
//    @Singleton
//    fun provideOrmaDatabase(context: Context): OrmaDatabase {
//        return OrmaDatabase.builder(context).build()
//    }

    @Provides
    fun provideActivityMainBinding(context: Context): ActivityMainBinding{
        return DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.activity_main, null, false)
    }
}