package com.grapecity.debugrank.injection.module;

import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.LanguagePuzzle;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.services.log.ILogger;
import com.grapecity.debugrank.javalib.services.repo.IDataRepository;
import com.grapecity.debugrank.javalib.services.repo.IUserRepository;
import com.grapecity.debugrank.javalib.services.web.IWebClient;

import java.util.List;

import com.grapecity.debugrank.test.common.MemoryDataRepository;
import com.grapecity.debugrank.test.common.MemoryUserRepository;
import io.realm.Realm;

/**
 * Created by chrisr on 2/29/2016.
 */
public class TestRepositoryModule extends RepositoryModule
{
    public TestRepositoryModule()
    {

    }

    @Override
    IDataRepository provideDataRepository(IWebClient<List<CodeLine>, LanguagePuzzle> codeWebClient, IWebClient<List<ProgrammingLanguage>, Void> languagesWebClient, IWebClient<List<Puzzle>, ProgrammingLanguage> puzzlesWebClient)
    {
        //return in memory repo's for espresso tests
        return new MemoryDataRepository();
    }

    @Override
    IUserRepository provideUserRepository(Realm realm, ILogger logger)
    {
        //return in memory repo's for espresso tests
        return new MemoryUserRepository();
    }
}
