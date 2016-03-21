package jp.gcreate.sample.kotlindaggerdatabinding.di;

import javax.inject.Singleton;

import dagger.Component;
import jp.gcreate.sample.kotlindaggerdatabinding.MainActivity;

/**
 * Copyright 2016 G-CREATE
 *
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MainActivity mainActivity);
}