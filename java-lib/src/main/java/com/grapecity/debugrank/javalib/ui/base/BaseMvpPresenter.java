package com.grapecity.debugrank.javalib.ui.base;

import com.grapecity.debugrank.javalib.services.thread.IAppThreads;

/**
 * Created by chrisr on 2/29/2016.
 */
public abstract class BaseMvpPresenter<T extends IMvpView, E extends IMvpInteractor> implements IMvpPresenter<T>
{
    protected T mvpView;
    protected final E mvpInteractor;
    protected final IAppThreads appThreads;

    protected BaseMvpPresenter(IAppThreads appThreads, E mvpInteractor)
    {
        this.appThreads = appThreads;
        this.mvpInteractor = mvpInteractor;
    }

    @Override
    public void attachView(T mvpView)
    {
        this.mvpView = mvpView;
    }
}
