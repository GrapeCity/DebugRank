package com.grapecity.debugrank.javalib.ui.solve;

import com.grapecity.debugrank.javalib.services.thread.IAppThreads;
import com.grapecity.debugrank.javalib.ui.base.BaseMvpPresenter;
import com.grapecity.debugrank.javalib.ui.solve.code.ISolveCodeView;
import com.grapecity.debugrank.javalib.ui.solve.result.ISolveResultView;

/**
 * Created by chrisr on 3/31/2016.
 */
public class SolvePresenterImpl extends BaseMvpPresenter<ISolveChildView, ISolveInteractor> implements ISolvePresenter
{
    protected ISolveCodeView solveCodeView;
    protected ISolveResultView solveResultView;

    public SolvePresenterImpl(IAppThreads appThreads, ISolveInteractor mvpInteractor)
    {
        super(appThreads, mvpInteractor);
    }

    @Override
    public void attachView(ISolveChildView mvpView)
    {
        if(mvpView instanceof ISolveView)
        {
            super.attachView(mvpView);
        }
        else if(mvpView instanceof ISolveCodeView)
        {
            solveCodeView = (ISolveCodeView) mvpView;
        }
        else if(mvpView instanceof ISolveResultView)
        {
            solveResultView = (ISolveResultView) mvpView;
        }
    }

    @Override
    public void loadCode()
    {
        solveCodeView.loadCode();
    }

    @Override
    public void uploadCode()
    {
        mvpView.codeCompiling();
        solveResultView.compileCode(solveCodeView.getCodeToCompile());
    }
}
