package com.grapecity.debugrank.injection.module;

import android.app.Application;

import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.LanguagePuzzle;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.services.log.ILogger;
import com.grapecity.debugrank.javalib.services.repo.DataRepositoryImpl;
import com.grapecity.debugrank.javalib.services.repo.IDataRepository;
import com.grapecity.debugrank.javalib.services.repo.IUserRepository;
import com.grapecity.debugrank.javalib.services.web.IWebClient;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.grapecity.debugrank.services.repo.UserRealmRepository;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by chrisr on 2/29/2016.
 */
@Module(includes = {AppModule.class, NetModule.class})
public class RepositoryModule
{
    public RepositoryModule()
    {

    }

    @Provides
    @Singleton
    RealmConfiguration provideRealmConfiguration(Application application)
    {
        return new RealmConfiguration.Builder(application).build();
    }

    @Provides
    @Singleton
    Realm provideRealm(RealmConfiguration realmConfiguration)
    {
        return Realm.getInstance(realmConfiguration);
    }

    @Provides
    @Singleton
    IDataRepository provideDataRepository(IWebClient<List<CodeLine>, LanguagePuzzle> codeWebClient, IWebClient<List<ProgrammingLanguage>, Void> languagesWebClient, IWebClient<List<Puzzle>, ProgrammingLanguage> puzzlesWebClient)
    {
        return new DataRepositoryImpl(languagesWebClient, puzzlesWebClient, codeWebClient);
    }

    @Provides
    @Singleton
    IUserRepository provideUserRepository(Realm realm, ILogger logger)
    {
        return new UserRealmRepository(realm, logger);
    }
}
