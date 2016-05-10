package com.grapecity.debugrank.javalib.ui.solve;

import com.grapecity.debugrank.javalib.ui.base.BasePresenterImplTestCase;
import com.grapecity.debugrank.javalib.ui.languages.ILanguagesInteractor;
import com.grapecity.debugrank.javalib.ui.languages.ILanguagesPresenter;
import com.grapecity.debugrank.javalib.ui.languages.ILanguagesView;
import com.grapecity.debugrank.javalib.ui.languages.LanguagesPresenterImpl;
import com.grapecity.debugrank.javalib.ui.solve.code.ISolveCodeView;
import com.grapecity.debugrank.javalib.ui.solve.result.ISolveResultView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import rx.Observable;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by chrisripple on 4/19/16.
 */
public class SolvePresenterImplTests  extends BasePresenterImplTestCase<ISolveInteractor, SolvePresenterImpl, ISolveView>
{
    ISolveCodeView solveCodeView;
    ISolveResultView solveResultView;

    @Before
    public void init()
    {
        solveCodeView = Mockito.mock(ISolveCodeView.class);
        solveResultView = Mockito.mock(ISolveResultView.class);

        ISolveInteractor interactor = mock(ISolveInteractor.class);

        view = mock(ISolveView.class);

        presenter = new SolvePresenterImpl(super.getAppThreads(), interactor);

        presenter.attachView(view);
        presenter.attachView(solveCodeView);
        presenter.attachView(solveResultView);
    }

    @Test
    public void testAttachViewSolveCode()
    {
        assertEquals(solveCodeView, presenter.solveCodeView);
    }

    @Test
    public void testAttachViewSolveResult()
    {
        assertEquals(solveResultView, presenter.solveResultView);
    }

    @Test
    public void testUploadCode()
    {
        doReturn(null).when(solveCodeView).getCodeToCompile();

        presenter.uploadCode();

        verify(solveCodeView, times(1)).getCodeToCompile();
        verify(solveResultView, times(1)).compileCode(null);
    }
}
