package com.grapecity.debugrank.javalib.ui.languages;

import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.ui.base.IMvpPresenter;

/**
 * Created by chrisr on 2/29/2016.
 */
public interface ILanguagesPresenter extends IMvpPresenter<ILanguagesView>
{
    void loadProgrammingLanguages();

    void loadAggregatedBugPoints();

    void programmingLanguageClicked(ProgrammingLanguage programmingLanguage);
}
