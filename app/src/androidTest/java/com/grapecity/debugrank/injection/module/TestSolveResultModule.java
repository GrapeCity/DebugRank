package com.grapecity.debugrank.injection.module;

import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.services.thread.IAppThreads;
import com.grapecity.debugrank.javalib.ui.solve.result.ISolveResultInteractor;
import com.grapecity.debugrank.javalib.ui.solve.result.ISolveResultPresenter;
import com.grapecity.debugrank.javalib.ui.solve.result.SolveResultPresenterImpl;

import java.util.List;

import com.grapecity.debugrank.TestMyApp;
import com.grapecity.debugrank.test.common.StaticProperties;

import rx.android.schedulers.AndroidSchedulers;

public class TestSolveResultModule extends SolveResultModule
{
    private final TestMyApp app;

    public TestSolveResultModule(TestMyApp app)
    {
        this.app = app;
    }

    @Override
    ISolveResultPresenter providesSolveResultPresenter(IAppThreads appThreads, ISolveResultInteractor solveResultInteractor)
    {
        return new TestSolveResultPresenterImpl(appThreads, solveResultInteractor);
    }

    private class TestSolveResultPresenterImpl extends SolveResultPresenterImpl
    {
        public TestSolveResultPresenterImpl(IAppThreads appThreads, ISolveResultInteractor mvpInteractor)
        {
            super(appThreads, mvpInteractor);
        }

        @Override
        public void compileCode(List<CodeLine> codeLines)
        {
            //allows us to control on a per unit test basis
            if(StaticProperties.getInstance().isInternetUploadsForever())
            {
                super.mvpView.codeCompiling();

                //nothing else happens, so it's "uploading" forever
            }
            else
            {
                super.compileCode(codeLines);
            }
        }
    }
}
