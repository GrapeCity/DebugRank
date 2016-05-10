package com.grapecity.debugrank.injection.module;

import com.grapecity.debugrank.javalib.services.thread.IAppThreads;
import com.grapecity.debugrank.javalib.ui.solve.code.ISolveCodeInteractor;
import com.grapecity.debugrank.javalib.ui.solve.code.ISolveCodePresenter;
import com.grapecity.debugrank.javalib.ui.solve.code.SolveCodePresenterImpl;

import com.grapecity.debugrank.TestMyApp;
import com.grapecity.debugrank.test.common.StaticProperties;

public class TestSolveCodeModule extends SolveCodeModule
{
    private final TestMyApp app;

    public TestSolveCodeModule(TestMyApp app)
    {
        this.app = app;
    }

    @Override
    ISolveCodePresenter providesSolveCodePresenter(IAppThreads appThreads, ISolveCodeInteractor solveCodeInteractor)
    {
        return new TestSolveCodePresenterImpl(appThreads,solveCodeInteractor);
    }

    private class TestSolveCodePresenterImpl extends SolveCodePresenterImpl
    {
        public TestSolveCodePresenterImpl(IAppThreads appThreads, ISolveCodeInteractor solveCodeInteractor)
        {
            super(appThreads, solveCodeInteractor);
        }

        @Override
        public void loadCode()
        {
            if(StaticProperties.getInstance().isInternetUploadsForever())
            {
                //nothing else happens, so it's "uploading" forever
            }
            else
            {
                super.loadCode();
            }
        }
    }
}
