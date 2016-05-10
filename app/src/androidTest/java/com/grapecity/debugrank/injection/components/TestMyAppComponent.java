package com.grapecity.debugrank.injection.components;


import javax.inject.Singleton;

import dagger.Component;
import com.grapecity.debugrank.TestMyApp;
import com.grapecity.debugrank.injection.module.RepositoryModule;


@Singleton
@Component(modules = {RepositoryModule.class})
public interface TestMyAppComponent
{
    void inject(TestMyApp app);
}