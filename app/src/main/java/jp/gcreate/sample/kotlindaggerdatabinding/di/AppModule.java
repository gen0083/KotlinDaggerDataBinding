package jp.gcreate.sample.kotlindaggerdatabinding.di;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jp.gcreate.sample.kotlindaggerdatabinding.databases.OrmaDatabase;

/**
 * Copyright 2016 G-CREATE
 */
@Module
public class AppModule {
    private final Context context;

    public AppModule(Context context){
        this.context = context;
    }

    @Provides
    public Context provideContext(){
        return context;
    }

    @Provides
    @Singleton
    @Named("injectedString")
    public String provideInjectedString(){
        return "hoge from app module";
    }

    @Provides
    @Named("hogeString")
    public String provideHogeString(Context context){
        return context.getClass().getSimpleName();
    }

    @Provides
    @Singleton
    public OrmaDatabase provideOrmaDatabase(Context context){
        return OrmaDatabase.builder(context).build();
    }

}
