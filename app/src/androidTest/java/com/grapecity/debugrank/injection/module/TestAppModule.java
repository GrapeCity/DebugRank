package com.grapecity.debugrank.injection.module;

import android.app.Application;

import com.grapecity.debugrank.javalib.services.thread.IAppThreads;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

public class TestAppModule extends AppModule
{
    public TestAppModule(Application application)
    {
        super(application);
    }

    @Override
    IAppThreads providesAppThreads()
    {
        //run everything on main thread for espresso tests
        return new IAppThreads()
        {
            @Override
            public Scheduler mainThread()
            {
                return AndroidSchedulers.mainThread();
            }

            @Override
            public Scheduler newThread()
            {
                return AndroidSchedulers.mainThread();
            }
        };
    }
}