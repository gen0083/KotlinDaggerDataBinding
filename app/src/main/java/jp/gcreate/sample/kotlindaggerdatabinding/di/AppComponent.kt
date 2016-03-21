package jp.gcreate.sample.kotlindaggerdatabinding.di

import dagger.Component
import jp.gcreate.sample.kotlindaggerdatabinding.MainActivity
import javax.inject.Singleton

/**
 * Copyright 2016 G-CREATE
 *
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}