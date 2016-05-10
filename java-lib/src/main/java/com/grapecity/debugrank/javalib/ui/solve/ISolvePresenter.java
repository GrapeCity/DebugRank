package com.grapecity.debugrank.javalib.ui.solve;

import com.grapecity.debugrank.javalib.ui.base.IMvpPresenter;
import com.grapecity.debugrank.javalib.ui.solve.code.ISolveCodeView;

/**
 * Created by chrisr on 3/31/2016.
 */
public interface ISolvePresenter extends IMvpPresenter<ISolveChildView>
{
    void loadCode();

    void uploadCode();
}