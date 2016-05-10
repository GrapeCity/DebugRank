package com.grapecity.debugrank.javalib.ui.solve.result;

import com.grapecity.debugrank.javalib.ui.base.BasePresenterImplTestCase;
import com.grapecity.debugrank.javalib.ui.solve.ISolveView;
import com.grapecity.debugrank.javalib.ui.solve.code.ISolveCodeInteractor;
import com.grapecity.debugrank.javalib.ui.solve.code.ISolveCodeView;
import com.grapecity.debugrank.javalib.ui.solve.code.SolveCodePresenterImpl;

import org.junit.Before;
import org.junit.Test;

import rx.Observable;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by chrisripple on 4/20/16.
 */
public class SolveResultPresenterImplTests extends BasePresenterImplTestCase<ISolveResultInteractor, SolveResultPresenterImpl, ISolveResultView>
{
    ISolveView solveView;

    @Before
    public void init()
    {
        solveView = mock(ISolveView.class);

        interactor = mock(ISolveResultInteractor.class);

        doReturn(Observable.just(null)).when(interactor).compileCode(null, null, null);
        doReturn(Observable.just(null)).when(interactor).saveCompletedPuzzle(null, null);

        view = mock(ISolveResultView.class);

        presenter = new SolveResultPresenterImpl(super.getAppThreads(), interactor);
        presenter.attachView(view);
        presenter.attachParentView(solveView);
    }

    @Test
    public void testCompileCodePassed()
    {
        doReturn(true).when(interactor).didCodePassAllTestCases(0, null);

        presenter.compileCode(null);

        verify(view, times(1)).codeCompiling();

        verify(view, times(1)).codeCompiled(null, true, 0);
        verify(solveView, times(1)).codeCompiled(null, true, 0);

        verify(view, times(1)).puzzleSolved(null);
        verify(solveView, times(1)).puzzleSolved(null);
    }

    @Test
    public void testCompileCodeFailed()
    {
        doReturn(false).when(interactor).didCodePassAllTestCases(0, null);

        presenter.compileCode(null);

        verify(view, times(1)).codeCompiling();

        verify(view, times(1)).codeCompiled(null, false, 0);
        verify(solveView, times(1)).codeCompiled(null, false, 0);

        verify(view, times(0)).puzzleSolved(null);
        verify(solveView, times(0)).puzzleSolved(null);
    }

    @Test
    public void testCompileCodeError()
    {
        doReturn(Observable.error(new Exception())).when(interactor).compileCode(null, null, null);

        presenter.compileCode(null);

        verify(solveView, times(1)).unableToUploadAndCompileCode();
    }


}
