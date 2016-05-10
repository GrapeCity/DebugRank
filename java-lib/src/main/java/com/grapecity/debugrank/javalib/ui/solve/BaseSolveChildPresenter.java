package com.grapecity.debugrank.javalib.ui.solve;

import com.grapecity.debugrank.javalib.services.thread.IAppThreads;
import com.grapecity.debugrank.javalib.ui.base.BaseMvpPresenter;
import com.grapecity.debugrank.javalib.ui.base.IMvpInteractor;
import com.grapecity.debugrank.javalib.ui.solve.result.ISolveResultInteractor;
import com.grapecity.debugrank.javalib.ui.solve.result.ISolveResultPresenter;
import com.grapecity.debugrank.javalib.ui.solve.result.ISolveResultView;

public abstract class BaseSolveChildPresenter<T extends ISolveChildView, E extends IMvpInteractor> extends BaseMvpPresenter<T, E> implements ISolveChildPresenter<T>
{
    protected ISolveView parentView;

    protected BaseSolveChildPresenter(IAppThreads appThreads, E mvpInteractor)
    {
        super(appThreads, mvpInteractor);
    }


    @Override
    public void attachParentView(ISolveView solveView)
    {
        parentView = solveView;
    }
}
