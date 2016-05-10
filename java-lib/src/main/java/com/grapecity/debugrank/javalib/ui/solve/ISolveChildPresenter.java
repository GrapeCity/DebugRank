package com.grapecity.debugrank.javalib.ui.solve;

import com.grapecity.debugrank.javalib.ui.base.IMvpPresenter;
import com.grapecity.debugrank.javalib.ui.base.IMvpView;
import com.grapecity.debugrank.javalib.ui.solve.code.ISolveCodeView;

public interface ISolveChildPresenter<T extends IMvpView> extends IMvpPresenter<T>
{
    void attachParentView(ISolveView solveView);
}