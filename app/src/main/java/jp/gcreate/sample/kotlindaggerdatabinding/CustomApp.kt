package jp.gcreate.sample.kotlindaggerdatabinding

import android.app.Application
import jp.gcreate.sample.kotlindaggerdatabinding.di.AppComponent
import jp.gcreate.sample.kotlindaggerdatabinding.di.AppModule
import jp.gcreate.sample.kotlindaggerdatabinding.di.DaggerAppComponent

/**
 * Copyright 2016 G-CREATE
 *
 */
class CustomApp: Application() {
    companion object{
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}