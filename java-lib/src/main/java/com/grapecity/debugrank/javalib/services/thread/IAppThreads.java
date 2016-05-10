package com.grapecity.debugrank.javalib.services.thread;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;

/**
 * Created by chrisripple on 4/7/16.
 */
public interface IAppThreads
{
    Scheduler mainThread();

    Scheduler newThread();
}
