package com.grapecity.debugrank;

import com.grapecity.debugrank.javalib.services.repo.IDataRepository;
import com.grapecity.debugrank.javalib.services.repo.IUserRepository;

import javax.inject.Inject;

import com.grapecity.debugrank.injection.components.DaggerTestMyAppComponent;
import com.grapecity.debugrank.injection.components.TestMyAppComponent;
import com.grapecity.debugrank.injection.module.AppModule;
import com.grapecity.debugrank.injection.module.LanguagesModule;
import com.grapecity.debugrank.injection.module.NetModule;
import com.grapecity.debugrank.injection.module.PuzzlesModule;
import com.grapecity.debugrank.injection.module.RepositoryModule;
import com.grapecity.debugrank.injection.module.SolveCodeModule;
import com.grapecity.debugrank.injection.module.SolveResultModule;
import com.grapecity.debugrank.injection.module.TestAppModule;
import com.grapecity.debugrank.injection.module.TestLanguagesModule;
import com.grapecity.debugrank.injection.module.TestNetModule;
import com.grapecity.debugrank.injection.module.TestPuzzlesModule;
import com.grapecity.debugrank.injection.module.TestRepositoryModule;
import com.grapecity.debugrank.injection.module.TestSolveCodeModule;
import com.grapecity.debugrank.injection.module.TestSolveResultModule;


/**
 * Created by Ripple on 1/25/2016.
 */
public class TestMyApp extends MyApp
{
    //need to make visible for espresso tests
    @Inject
    IUserRepository userRepository;

    //need to make visible for espresso tests
    @Inject
    IDataRepository dataRepository;

    @Override
    public void onCreate()
    {
        super.onCreate();

        TestMyAppComponent testMyAppComponent = DaggerTestMyAppComponent.builder()
                .appModule(getAppModule())
                .netModule(getNetModule("", ""))
                .repositoryModule(getRepositoryModule())
                .build();

        testMyAppComponent.inject(this);
    }

    @Override
    protected AppModule getAppModule()
    {
        return new TestAppModule(this);
    }

    @Override
    protected NetModule getNetModule(String github, String hackerrank)
    {
        return new TestNetModule(this, github, hackerrank);
    }

    @Override
    protected RepositoryModule getRepositoryModule()
    {
        return new TestRepositoryModule();
    }

    @Override
    protected SolveCodeModule getSolveCodeModule()
    {
        return new TestSolveCodeModule(this);
    }

    @Override
    protected SolveResultModule getSolveResultModule()
    {
        return new TestSolveResultModule(this);
    }

    @Override
    protected LanguagesModule getLanguagesModule()
    {
        return new TestLanguagesModule(this);
    }

    @Override
    protected PuzzlesModule getPuzzlesModule()
    {
        return new TestPuzzlesModule(this);
    }

    public IDataRepository getDataRepository()
    {
        return dataRepository;
    }

    public IUserRepository getUserRepository()
    {
        return userRepository;
    }
}
