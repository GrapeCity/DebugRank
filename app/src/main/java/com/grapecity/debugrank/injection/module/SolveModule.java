package com.grapecity.debugrank.injection.module;

import com.grapecity.debugrank.javalib.services.thread.IAppThreads;
import com.grapecity.debugrank.javalib.ui.solve.ISolveInteractor;
import com.grapecity.debugrank.javalib.ui.solve.ISolvePresenter;
import com.grapecity.debugrank.javalib.ui.solve.SolveInteractorImpl;
import com.grapecity.debugrank.javalib.ui.solve.SolvePresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chrisr on 2/29/2016.
 */
@Module(includes = {AppModule.class, NetModule.class})
public class SolveModule
{
    public SolveModule()
    {

    }

    @Provides
    @Singleton
    ISolveInteractor providesSolveInteractor()
    {
        return new SolveInteractorImpl();
    }

    @Provides
    @Singleton
    ISolvePresenter providesSolvePresenter(IAppThreads appThreads, ISolveInteractor solveInteractor)
    {
        return new SolvePresenterImpl(appThreads, solveInteractor);
    }
}
