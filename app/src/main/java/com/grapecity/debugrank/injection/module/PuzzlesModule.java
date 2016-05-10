package com.grapecity.debugrank.injection.module;

import com.grapecity.debugrank.javalib.services.repo.IDataRepository;
import com.grapecity.debugrank.javalib.services.repo.IUserRepository;
import com.grapecity.debugrank.javalib.services.thread.IAppThreads;
import com.grapecity.debugrank.javalib.ui.puzzles.IPuzzlesInteractor;
import com.grapecity.debugrank.javalib.ui.puzzles.IPuzzlesPresenter;
import com.grapecity.debugrank.javalib.ui.puzzles.PuzzlesInteractorImpl;
import com.grapecity.debugrank.javalib.ui.puzzles.PuzzlesPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chrisr on 2/29/2016.
 */
@Module(includes = {AppModule.class, NetModule.class, RepositoryModule.class})
public class PuzzlesModule
{
    public PuzzlesModule()
    {

    }

    @Provides
    @Singleton
    IPuzzlesInteractor providesLanguagesInteractor(IDataRepository dataRepository, IUserRepository userRepository)
    {
        return new PuzzlesInteractorImpl(dataRepository, userRepository);
    }

    @Provides
    @Singleton
    IPuzzlesPresenter providesPuzzlesPresenter(IAppThreads appThreads, IPuzzlesInteractor puzzlesInteractor)
    {
        return new PuzzlesPresenterImpl(appThreads, puzzlesInteractor);
    }
}
