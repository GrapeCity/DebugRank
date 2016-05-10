package com.grapecity.debugrank.javalib.ui.solve.code;

import com.grapecity.debugrank.javalib.ui.base.BasePresenterImplTestCase;
import com.grapecity.debugrank.javalib.ui.puzzles.IPuzzlesInteractor;
import com.grapecity.debugrank.javalib.ui.puzzles.IPuzzlesView;
import com.grapecity.debugrank.javalib.ui.puzzles.PuzzlesPresenterImpl;
import com.grapecity.debugrank.javalib.ui.solve.ISolveView;
import com.grapecity.debugrank.javalib.ui.solve.SolvePresenterImpl;

import org.junit.Before;
import org.junit.Test;

import rx.Observable;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by chrisripple on 4/20/16.
 */
public class SolveCodePresenterImplTests extends BasePresenterImplTestCase<ISolveCodeInteractor, SolveCodePresenterImpl, ISolveCodeView>
{
    ISolveView parentView;

    @Before
    public void init()
    {
        interactor = mock(ISolveCodeInteractor.class);
        doReturn(Observable.just(null)).when(interactor).loadCodeFile(null, null);

        parentView = mock(ISolveView.class);
        view = mock(ISolveCodeView.class);

        presenter = new SolveCodePresenterImpl(super.getAppThreads(), interactor);
        presenter.attachView(view);
        presenter.attachParentView(parentView);
    }

    @Test
    public void testLoadCode()
    {
        presenter.loadCode();

        verify(view, times(1)).codeLoaded(null);
        verify(parentView, times(0)).unableToLoadSolveCode();
    }

    @Test
    public void testLoadCodeFailure()
    {
        doReturn(Observable.error(new Exception())).when(interactor).loadCodeFile(null, null);

        presenter.loadCode();

        verify(view, times(0)).codeLoaded(null);
        verify(parentView, times(1)).unableToLoadSolveCode();
    }
}
