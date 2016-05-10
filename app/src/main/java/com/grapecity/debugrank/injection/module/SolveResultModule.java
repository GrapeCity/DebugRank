package com.grapecity.debugrank.injection.module;

import com.grapecity.debugrank.javalib.entities.HackerRankCompileParams;
import com.grapecity.debugrank.javalib.entities.HackerRankResult;
import com.grapecity.debugrank.javalib.services.compile.ICompilerApi;
import com.grapecity.debugrank.javalib.services.repo.IDataRepository;
import com.grapecity.debugrank.javalib.services.repo.IUserRepository;
import com.grapecity.debugrank.javalib.services.thread.IAppThreads;
import com.grapecity.debugrank.javalib.services.web.IWebClient;
import com.grapecity.debugrank.javalib.ui.solve.result.ISolveResultInteractor;
import com.grapecity.debugrank.javalib.ui.solve.result.ISolveResultPresenter;
import com.grapecity.debugrank.javalib.ui.solve.result.SolveResultInteractorImpl;
import com.grapecity.debugrank.javalib.ui.solve.result.SolveResultPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
/**
 * Created by chrisr on 2/29/2016.
 */
@Module(includes = {AppModule.class, NetModule.class, RepositoryModule.class, CompilerModule.class})
public class SolveResultModule
{
    public SolveResultModule()
    {

    }

    @Provides
    @Singleton
    ISolveResultInteractor providesSolveResultInteractor(IDataRepository dataRepository, IUserRepository userRepository, ICompilerApi compilerApi, IWebClient<HackerRankResult, HackerRankCompileParams> hackerRankWebClient)
    {
        return new SolveResultInteractorImpl(dataRepository, userRepository, hackerRankWebClient, compilerApi);
    }

    @Provides
    @Singleton
    ISolveResultPresenter providesSolveResultPresenter(IAppThreads appThreads, ISolveResultInteractor solveResultInteractor)
    {
        return new SolveResultPresenterImpl(appThreads, solveResultInteractor);
    }
}