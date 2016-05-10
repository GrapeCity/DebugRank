package com.grapecity.debugrank.injection.components;


import javax.inject.Singleton;

import dagger.Component;
import com.grapecity.debugrank.injection.module.AppModule;
import com.grapecity.debugrank.injection.module.NetModule;
import com.grapecity.debugrank.injection.module.SolveCodeModule;
import com.grapecity.debugrank.ui.solve.code.SolveCodeFragment;


@Singleton
@Component(modules = {AppModule.class, NetModule.class, SolveCodeModule.class})
public interface SolveCodeComponent
{
    void inject(SolveCodeFragment fragment);
}