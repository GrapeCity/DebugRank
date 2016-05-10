package com.grapecity.debugrank.injection.module;

import com.grapecity.debugrank.javalib.services.html.IHtmlToDisplay;
import com.grapecity.debugrank.javalib.services.repo.IDataRepository;
import com.grapecity.debugrank.javalib.services.repo.IUserRepository;
import com.grapecity.debugrank.javalib.services.syntax.ISyntaxHighlighter;
import com.grapecity.debugrank.javalib.services.syntax.PrettifySyntaxHighlighter;
import com.grapecity.debugrank.javalib.services.thread.IAppThreads;
import com.grapecity.debugrank.javalib.ui.solve.code.ISolveCodeInteractor;
import com.grapecity.debugrank.javalib.ui.solve.code.ISolveCodePresenter;
import com.grapecity.debugrank.javalib.ui.solve.code.SolveCodeInteractorImpl;
import com.grapecity.debugrank.javalib.ui.solve.code.SolveCodePresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.grapecity.debugrank.services.html.AndroidHtmlToDisplay;

/**
 * Created by chrisr on 2/29/2016.
 */
@Module(includes = {AppModule.class, NetModule.class, RepositoryModule.class})
public class SolveCodeModule
{
    public SolveCodeModule()
    {

    }

    @Provides
    @Singleton
    ISyntaxHighlighter providesSyntaxHighlighter()
    {
        return new PrettifySyntaxHighlighter();
    }

    @Provides
    @Singleton
    IHtmlToDisplay providesHtmlToDisplay()
    {
        return new AndroidHtmlToDisplay();
    }

    @Provides
    @Singleton
    ISolveCodeInteractor providesSolveCodeInteractor(IDataRepository dataRepository, IUserRepository userRepository, ISyntaxHighlighter syntaxHighlighter, IHtmlToDisplay htmlToDisplay)
    {
        return new SolveCodeInteractorImpl(dataRepository, userRepository, syntaxHighlighter, htmlToDisplay);
    }

    @Provides
    @Singleton
    ISolveCodePresenter providesSolveCodePresenter(IAppThreads appThreads, ISolveCodeInteractor solveCodeInteractor)
    {
        return new SolveCodePresenterImpl(appThreads, solveCodeInteractor);
    }
}
