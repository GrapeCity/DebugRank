package com.grapecity.debugrank;

import android.app.Application;

import com.grapecity.xuni.core.LicenseManager;

import com.grapecity.debugrank.javalib.constants.Api;
import com.grapecity.debugrank.constants.XuniLicense;
import com.grapecity.debugrank.injection.components.DaggerLanguagesComponent;
import com.grapecity.debugrank.injection.components.DaggerPuzzlesComponent;
import com.grapecity.debugrank.injection.components.DaggerSolveCodeComponent;
import com.grapecity.debugrank.injection.components.DaggerSolveComponent;
import com.grapecity.debugrank.injection.components.DaggerSolveResultComponent;
import com.grapecity.debugrank.injection.components.LanguagesComponent;
import com.grapecity.debugrank.injection.components.PuzzlesComponent;
import com.grapecity.debugrank.injection.components.SolveCodeComponent;
import com.grapecity.debugrank.injection.components.SolveComponent;
import com.grapecity.debugrank.injection.components.SolveResultComponent;
import com.grapecity.debugrank.injection.module.AppModule;
import com.grapecity.debugrank.injection.module.LanguagesModule;
import com.grapecity.debugrank.injection.module.NetModule;
import com.grapecity.debugrank.injection.module.PuzzlesModule;
import com.grapecity.debugrank.injection.module.RepositoryModule;
import com.grapecity.debugrank.injection.module.SolveCodeModule;
import com.grapecity.debugrank.injection.module.SolveModule;
import com.grapecity.debugrank.injection.module.SolveResultModule;


/**
 * Created by Ripple on 1/25/2016.
 */
public class MyApp extends Application
{
    protected LanguagesComponent languagesComponent;
    protected PuzzlesComponent puzzlesComponent;
    protected SolveComponent solveComponent;
    protected SolveCodeComponent solveCodeComponent;
    protected SolveResultComponent solveResultComponent;

    @Override
    public void onCreate()
    {
        super.onCreate();

        final String github = Api.GITHUB_API;
        final String hackerrank = Api.HACKERRANK_API;

        AppModule appModule = getAppModule();
        NetModule netModule = getNetModule(github, hackerrank);
        LanguagesModule languagesModule = getLanguagesModule();
        PuzzlesModule puzzlesModule = getPuzzlesModule();
        SolveCodeModule solveCodeModule = getSolveCodeModule();
        SolveResultModule solveResultModule = getSolveResultModule();
        SolveModule solveModule = getSolveModule();
        RepositoryModule repositoryModule = getRepositoryModule();

        languagesComponent = DaggerLanguagesComponent.builder()
                .appModule(appModule)
                .netModule(netModule)
                .repositoryModule(repositoryModule)
                .languagesModule(languagesModule)
                .build();

        puzzlesComponent = DaggerPuzzlesComponent.builder()
                .appModule(appModule)
                .netModule(netModule)
                .repositoryModule(repositoryModule)
                .puzzlesModule(puzzlesModule)
                .build();

        solveComponent = DaggerSolveComponent.builder()
                .appModule(appModule)
                .solveModule(solveModule)
                .netModule(netModule)
                .build();

        solveCodeComponent = DaggerSolveCodeComponent.builder()
                .appModule(appModule)
                .netModule(netModule)
                .repositoryModule(repositoryModule)
                .solveCodeModule(solveCodeModule)
                .build();

        solveResultComponent = DaggerSolveResultComponent.builder()
                .appModule(appModule)
                .netModule(netModule)
                .repositoryModule(repositoryModule)
                .solveResultModule(solveResultModule)
                .build();

        LicenseManager.KEY = XuniLicense.KEY;
    }

    protected AppModule getAppModule()
    {
        return new AppModule(this);
    }

    protected NetModule getNetModule(String github, String hackerrank)
    {
        return new NetModule(github, hackerrank);
    }

    protected LanguagesModule getLanguagesModule()
    {
        return new LanguagesModule();
    }

    protected PuzzlesModule getPuzzlesModule()
    {
        return new PuzzlesModule();
    }

    protected SolveCodeModule getSolveCodeModule()
    {
        return new SolveCodeModule();
    }

    protected SolveResultModule getSolveResultModule()
    {
        return new SolveResultModule();
    }

    protected SolveModule getSolveModule()
    {
        return new SolveModule();
    }

    protected RepositoryModule getRepositoryModule()
    {
        return new RepositoryModule();
    }

    public PuzzlesComponent getPuzzlesComponent()
    {
        return puzzlesComponent;
    }

    public LanguagesComponent getLanguagesComponent()
    {
        return languagesComponent;
    }

    public SolveComponent getSolveComponent()
    {
        return solveComponent;
    }

    public SolveCodeComponent getSolveCodeComponent()
    {
        return solveCodeComponent;
    }

    public SolveResultComponent getSolveResultComponent()
    {
        return solveResultComponent;
    }
}
