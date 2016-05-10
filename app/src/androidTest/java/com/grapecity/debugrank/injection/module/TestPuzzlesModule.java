package com.grapecity.debugrank.injection.module;

import com.grapecity.debugrank.javalib.services.thread.IAppThreads;
import com.grapecity.debugrank.javalib.ui.puzzles.IPuzzlesInteractor;
import com.grapecity.debugrank.javalib.ui.puzzles.IPuzzlesPresenter;
import com.grapecity.debugrank.javalib.ui.puzzles.PuzzlesPresenterImpl;

import com.grapecity.debugrank.TestMyApp;
import com.grapecity.debugrank.test.common.StaticProperties;

public class TestPuzzlesModule extends PuzzlesModule
{
    private final TestMyApp app;

    public TestPuzzlesModule(TestMyApp app)
    {
        this.app = app;
    }

    @Override
    IPuzzlesPresenter providesPuzzlesPresenter(IAppThreads appThreads, IPuzzlesInteractor puzzlesInteractor)
    {
        return new TestPuzzlesPresenterImpl(appThreads, puzzlesInteractor);
    }

    private class TestPuzzlesPresenterImpl extends PuzzlesPresenterImpl
    {
        public TestPuzzlesPresenterImpl(IAppThreads appThreads, IPuzzlesInteractor puzzlesInteractor)
        {
            super(appThreads, puzzlesInteractor);
        }

        @Override
        public void loadPuzzles()
        {
            if(StaticProperties.getInstance().isInternetUploadsForever())
            {
                //nothing else happens, so it's "uploading" forever
            }
            else
            {
                super.loadPuzzles();
            }
        }
    }
}
