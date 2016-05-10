package com.grapecity.debugrank.services.thread;

import com.grapecity.debugrank.javalib.services.thread.IAppThreads;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chrisripple on 4/7/16.
 */
public class AndroidAppThreads implements IAppThreads
{
    @Override
    public Scheduler mainThread()
    {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler newThread()
    {
        return Schedulers.newThread();
    }
}
