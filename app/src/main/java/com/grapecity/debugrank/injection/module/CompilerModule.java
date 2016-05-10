package com.grapecity.debugrank.injection.module;

import com.grapecity.debugrank.javalib.services.compile.HackerRankCompilerApi;
import com.grapecity.debugrank.javalib.services.compile.ICompilerApi;
import com.grapecity.debugrank.javalib.services.log.ILogger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by chrisr on 2/29/2016.
 */
@Module(includes = AppModule.class)
public class CompilerModule
{
    public CompilerModule()
    {

    }

    @Provides
    @Singleton
    ICompilerApi provideCompilerApi(ILogger logger)
    {
        return new HackerRankCompilerApi(logger);
    }
}
