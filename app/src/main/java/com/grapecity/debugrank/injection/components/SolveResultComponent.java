package com.grapecity.debugrank.injection.components;


import javax.inject.Singleton;

import dagger.Component;
import com.grapecity.debugrank.injection.module.AppModule;
import com.grapecity.debugrank.injection.module.NetModule;
import com.grapecity.debugrank.injection.module.SolveResultModule;
import com.grapecity.debugrank.ui.solve.result.SolveResultFragment;


@Singleton
@Component(modules = {AppModule.class, NetModule.class, SolveResultModule.class})
public interface SolveResultComponent
{
    void inject(SolveResultFragment fragment);
}