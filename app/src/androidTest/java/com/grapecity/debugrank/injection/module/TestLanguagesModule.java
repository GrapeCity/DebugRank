package com.grapecity.debugrank.injection.module;

import com.grapecity.debugrank.javalib.services.thread.IAppThreads;
import com.grapecity.debugrank.javalib.ui.languages.ILanguagesInteractor;
import com.grapecity.debugrank.javalib.ui.languages.ILanguagesPresenter;
import com.grapecity.debugrank.javalib.ui.languages.LanguagesPresenterImpl;

import com.grapecity.debugrank.TestMyApp;
import com.grapecity.debugrank.test.common.StaticProperties;

public class TestLanguagesModule extends LanguagesModule
{
    private final TestMyApp app;

    public TestLanguagesModule(TestMyApp app)
    {
        this.app = app;
    }

    @Override
    ILanguagesPresenter providesLanguagesPresenter(IAppThreads appThreads, ILanguagesInteractor languagesInteractor)
    {
        return new TestLanguagesPresenterImpl(appThreads, languagesInteractor);
    }

    private class TestLanguagesPresenterImpl extends LanguagesPresenterImpl
    {
        public TestLanguagesPresenterImpl(IAppThreads appThreads, ILanguagesInteractor languagesInteractor)
        {
            super(appThreads, languagesInteractor);
        }

        @Override
        public void loadProgrammingLanguages()
        {
            if(StaticProperties.getInstance().isInternetUploadsForever())
            {
                //nothing else happens, so it's "uploading" forever
            }
            else
            {
                super.loadProgrammingLanguages();
            }
        }
    }
}
