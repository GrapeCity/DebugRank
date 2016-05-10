package com.grapecity.debugrank.javalib.ui.languages;

import com.grapecity.debugrank.javalib.entities.AggregatedBugsPoints;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.ui.base.IMvpInteractor;

import java.util.List;

import rx.Observable;


/**
 * Created by chrisr on 2/29/2016.
 */
public interface ILanguagesInteractor extends IMvpInteractor
{
    Observable<List<ProgrammingLanguage>>  loadProgrammingLanguages();

    Observable<AggregatedBugsPoints> loadAggregatedBugsPoints();
}
