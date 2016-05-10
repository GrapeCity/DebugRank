package com.grapecity.debugrank.injection.components;


import javax.inject.Singleton;

import dagger.Component;
import com.grapecity.debugrank.injection.module.AppModule;
import com.grapecity.debugrank.injection.module.SolveModule;
import com.grapecity.debugrank.ui.solve.SolveActivity;


@Singleton
@Component(modules = {AppModule.class, SolveModule.class})
public interface SolveComponent
{
    void inject(SolveActivity activity);
}