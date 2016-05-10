package com.grapecity.debugrank.javalib.ui.base;

import com.grapecity.debugrank.javalib.services.thread.IAppThreads;
import com.grapecity.debugrank.javalib.ui.puzzles.IPuzzlesInteractor;
import com.grapecity.debugrank.javalib.ui.puzzles.IPuzzlesView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

/**
 * Created by chrisripple on 4/19/16.
 */
public class BaseMvpPresenterTests
{
    BaseMvpPresenter<IPuzzlesView, IPuzzlesInteractor> presenter;

    IAppThreads appThreads;
    IPuzzlesInteractor interactor;


    @Before
    public void init()
    {
        appThreads = Mockito.mock(IAppThreads.class);
        interactor = Mockito.mock(IPuzzlesInteractor.class);

        presenter = new MockMvpPresenter(appThreads, interactor);
    }

    @Test
    public void constructor()
    {
        assertEquals(appThreads, presenter.appThreads);
        assertEquals(interactor, presenter.mvpInteractor);
    }

    @Test
    public void testAttachView()
    {
        IPuzzlesView view = Mockito.mock(IPuzzlesView.class);

        presenter.attachView(view);

        assertEquals(view, presenter.mvpView);
    }

    private class MockMvpPresenter extends BaseMvpPresenter<IPuzzlesView, IPuzzlesInteractor>
    {
        protected MockMvpPresenter(IAppThreads appThreads, IPuzzlesInteractor mvpInteractor)
        {
            super(appThreads, mvpInteractor);
        }
    }
}
