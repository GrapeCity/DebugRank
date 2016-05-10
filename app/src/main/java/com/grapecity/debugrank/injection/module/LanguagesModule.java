package com.grapecity.debugrank.injection.module;

import com.grapecity.debugrank.javalib.services.repo.IDataRepository;
import com.grapecity.debugrank.javalib.services.repo.IUserRepository;
import com.grapecity.debugrank.javalib.services.thread.IAppThreads;
import com.grapecity.debugrank.javalib.ui.languages.ILanguagesInteractor;
import com.grapecity.debugrank.javalib.ui.languages.ILanguagesPresenter;
import com.grapecity.debugrank.javalib.ui.languages.LanguagesInteractorImpl;
import com.grapecity.debugrank.javalib.ui.languages.LanguagesPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chrisr on 2/29/2016.
 */
@Module(includes = {AppModule.class, NetModule.class, RepositoryModule.class})
public class LanguagesModule
{
    public LanguagesModule()
    {

    }

    @Provides
    @Singleton
    ILanguagesInteractor providesLanguagesInteractor(IDataRepository dataRepository, IUserRepository userRepository)
    {
        return new LanguagesInteractorImpl(dataRepository, userRepository);
    }

    @Provides
    @Singleton
    ILanguagesPresenter providesLanguagesPresenter(IAppThreads appThreads, ILanguagesInteractor languagesInteractor)
    {
        return new LanguagesPresenterImpl(appThreads, languagesInteractor);
    }
}
