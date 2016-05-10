package com.grapecity.debugrank.javalib.ui.base;

/**
 * Created by chrisr on 2/29/2016.
 */
public interface IMvpPresenter<T extends IMvpView>
{
    void attachView(T mvpView);
}
