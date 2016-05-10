package com.grapecity.debugrank.injection.components;


import javax.inject.Singleton;

import dagger.Component;
import com.grapecity.debugrank.injection.module.AppModule;
import com.grapecity.debugrank.injection.module.NetModule;
import com.grapecity.debugrank.injection.module.PuzzlesModule;
import com.grapecity.debugrank.injection.module.RepositoryModule;
import com.grapecity.debugrank.ui.puzzles.PuzzlesActivity;


@Singleton
@Component(modules = {AppModule.class, NetModule.class, PuzzlesModule.class, RepositoryModule.class})
public interface PuzzlesComponent
{
    void inject(PuzzlesActivity activity);
}