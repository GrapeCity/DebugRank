package com.grapecity.debugrank.injection.module;

import android.app.Application;

import com.grapecity.debugrank.javalib.services.log.ILogger;
import com.grapecity.debugrank.javalib.services.thread.IAppThreads;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.grapecity.debugrank.services.log.AndroidLogger;
import com.grapecity.debugrank.services.thread.AndroidAppThreads;

@Module
public class AppModule
{
    Application mApplication;

    public AppModule(Application application)
    {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication()
    {
        return mApplication;
    }

    @Provides
    @Singleton
    ILogger providesLogger()
    {
        return new AndroidLogger();
    }

    @Provides
    @Singleton
    IAppThreads providesAppThreads()
    {
        return new AndroidAppThreads();
    }
}