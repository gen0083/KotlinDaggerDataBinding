package jp.gcreate.sample.kotlindaggerdatabinding

import android.app.Application
import android.content.Context

import jp.gcreate.sample.kotlindaggerdatabinding.di.AppComponent
import jp.gcreate.sample.kotlindaggerdatabinding.di.AppModule
import jp.gcreate.sample.kotlindaggerdatabinding.di.DaggerAppComponent

/**
 * Copyright 2016 G-CREATE

 */
internal class CustomApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {
        @JvmStatic
        fun getComponent(context: Context): AppComponent {
            val app = context.applicationContext as CustomApp
            return app.appComponent
        }
    }
}