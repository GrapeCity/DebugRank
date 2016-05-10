package com.grapecity.debugrank.javalib.ui.puzzles;

import com.grapecity.debugrank.javalib.services.thread.IAppThreads;
import com.grapecity.debugrank.javalib.ui.base.BasePresenterImplTestCase;
import com.grapecity.debugrank.javalib.ui.languages.ILanguagesInteractor;
import com.grapecity.debugrank.javalib.ui.languages.ILanguagesPresenter;
import com.grapecity.debugrank.javalib.ui.languages.ILanguagesView;
import com.grapecity.debugrank.javalib.ui.languages.LanguagesPresenterImpl;

import org.junit.Before;
import org.junit.Test;

import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by chrisr on 3/28/2016.
 */
public class PuzzlesPresenterImplTests extends BasePresenterImplTestCase<IPuzzlesInteractor, IPuzzlesPresenter, IPuzzlesView>
{
    @Before
    public void init()
    {
        interactor = mock(IPuzzlesInteractor.class);
        doReturn(Observable.just(null)).when(interactor).loadPuzzles(null);
        doReturn(Observable.just(null)).when(interactor).loadAggregatedBugsPoints(null);
        doReturn(Observable.just(null)).when(interactor).loadCompletedPuzzles(null);

        view = mock(IPuzzlesView.class);

        presenter = new PuzzlesPresenterImpl(super.getAppThreads(), interactor);
        presenter.attachView(view);
    }

    @Test
    public void loadPuzzles() throws InterruptedException
    {
        presenter.loadPuzzles();

        //verify that the view was called to refresh the programming languages twice
        //presenter.attachView
        //presenter.loadPuzzles
        verify(view, times(2)).puzzlesLoaded(null);
        verify(view, times(0)).unableToLoadPuzzles();
    }

    @Test
    public void loadPuzzlesFail() throws InterruptedException
    {
        doReturn(Observable.error(new Exception())).when(interactor).loadPuzzles(null);

        presenter.loadPuzzles();

        verify(view, times(1)).puzzlesLoaded(null);
        verify(view, times(1)).unableToLoadPuzzles();
    }

    @Test
    public void loadAggregatedBugPoints() throws InterruptedException
    {
        presenter.loadAggregatedBugPoints(null);
        //verify that the view was called to refresh the programming languages twice
        //presenter.attachView
        //presenter.loadAggregatedBugPoints
        verify(view, times(2)).aggregatedBugPointsLoaded(null);
    }

    @Test
    public void loadCompletedPuzzles() throws InterruptedException
    {
        presenter.loadCompletedPuzzles(null);
        //verify that the view was called to refresh the programming languages twice
        //presenter.attachView
        //presenter.loadCompletedPuzzles
        verify(view, times(2)).completedPuzzlesLoaded(null);
    }

    @Test
    public void puzzleClicked() throws InterruptedException
    {
        presenter.puzzleClicked(null);

        verify(view, times(1)).navigateToSolveScreen();
    }
}
