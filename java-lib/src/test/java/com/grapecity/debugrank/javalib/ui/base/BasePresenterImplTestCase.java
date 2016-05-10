package com.grapecity.debugrank.javalib.ui.base;

import com.grapecity.debugrank.javalib.services.thread.IAppThreads;
import com.grapecity.debugrank.javalib.ui.languages.ILanguagesPresenter;
import com.grapecity.debugrank.javalib.ui.languages.ILanguagesView;

import rx.schedulers.Schedulers;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 * Created by chrisripple on 4/18/16.
 */
public abstract class BasePresenterImplTestCase<I extends IMvpInteractor, P extends IMvpPresenter,V extends  IMvpView>
{
    protected I interactor;
    protected P presenter;
    protected V view;

    protected IAppThreads getAppThreads()
    {
        IAppThreads appThreads = mock(IAppThreads.class);
        doReturn(Schedulers.immediate()).when(appThreads).newThread();
        doReturn(Schedulers.immediate()).when(appThreads).mainThread();

        return appThreads;
    }
}
