package jp.gcreate.sample.kotlindaggerdatabinding;

import android.app.Application;
import android.content.Context;

import jp.gcreate.sample.kotlindaggerdatabinding.di.AppComponent;
import jp.gcreate.sample.kotlindaggerdatabinding.di.AppModule;
import jp.gcreate.sample.kotlindaggerdatabinding.di.DaggerAppComponent;

/**
 * Copyright 2016 G-CREATE

 */
class CustomApp extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static AppComponent getComponent(Context context){
        CustomApp app = (CustomApp) context.getApplicationContext();
        return app.appComponent;
    }
}