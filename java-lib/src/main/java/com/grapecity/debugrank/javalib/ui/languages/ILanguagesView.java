package com.grapecity.debugrank.javalib.ui.languages;

import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.ui.base.IMvpView;

import java.util.List;


/**
 * Created by chrisr on 2/29/2016.
 */
public interface ILanguagesView extends IMvpView
{
    void programmingLanguagesLoaded(List<ProgrammingLanguage> programmingLanguageList);

    void unableToLoadLanguages();

    void navigateToPuzzleScreen();
}
