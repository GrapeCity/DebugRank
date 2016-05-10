package com.grapecity.debugrank.javalib.ui.languages;

import com.grapecity.debugrank.javalib.entities.AggregatedBugPointsTests;
import com.grapecity.debugrank.javalib.entities.AggregatedBugsPoints;
import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.services.thread.IAppThreads;
import com.grapecity.debugrank.javalib.ui.base.BasePresenterImplTestCase;
import com.grapecity.debugrank.javalib.util.ObserverAdapter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import rx.Observable;
import rx.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;

/**
 * Created by chrisr on 3/28/2016.
 */
public class LanguagesPresenterImplTests extends BasePresenterImplTestCase<ILanguagesInteractor, ILanguagesPresenter, ILanguagesView>
{
    @Before
    public void init()
    {
        interactor = mock(ILanguagesInteractor.class);

        doReturn(Observable.just(null)).when(interactor).loadProgrammingLanguages();
        doReturn(Observable.just(null)).when(interactor).loadAggregatedBugsPoints();

        view = mock(ILanguagesView.class);

        presenter = new LanguagesPresenterImpl(super.getAppThreads(), interactor);

        presenter.attachView(view);
    }

    @Test
    public void loadProgrammingLanguages() throws InterruptedException
    {
        presenter.loadProgrammingLanguages();

        //verify that the view was called to refresh the programming languages twice
        //presenter.attachView
        //presenter.loadProgrammingLanguages
        verify(view, times(2)).programmingLanguagesLoaded(null);
        verify(view, times(0)).unableToLoadLanguages();
    }

    @Test
    public void loadProgrammingLanguagesFail() throws InterruptedException
    {
        doReturn(Observable.error(new Exception())).when(interactor).loadProgrammingLanguages();

        presenter.loadProgrammingLanguages();

        //called once for attachView
        verify(view, times(1)).programmingLanguagesLoaded(null);
        verify(view, times(1)).unableToLoadLanguages();
    }

    @Test
    public void loadAggregatedBugPoints() throws InterruptedException
    {
        presenter.loadAggregatedBugPoints();
        //verify that the view was called to refresh the programming languages twice
        //presenter.attachView
        //presenter.loadAggregatedBugPoints
        verify(view, times(2)).aggregatedBugPointsLoaded(null);
    }

    @Test
    public void programmingLanguageClicked() throws InterruptedException
    {
        presenter.programmingLanguageClicked(null);

        verify(view, times(1)).navigateToPuzzleScreen();
    }
}
