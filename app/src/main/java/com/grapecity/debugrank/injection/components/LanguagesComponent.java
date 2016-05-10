package com.grapecity.debugrank.injection.components;


import javax.inject.Singleton;

import dagger.Component;
import com.grapecity.debugrank.injection.module.AppModule;
import com.grapecity.debugrank.injection.module.LanguagesModule;
import com.grapecity.debugrank.injection.module.NetModule;
import com.grapecity.debugrank.injection.module.RepositoryModule;
import com.grapecity.debugrank.ui.languages.LanguagesActivity;


@Singleton
@Component(modules = {AppModule.class, NetModule.class, LanguagesModule.class, RepositoryModule.class})
public interface LanguagesComponent
{
    void inject(LanguagesActivity activity);
}