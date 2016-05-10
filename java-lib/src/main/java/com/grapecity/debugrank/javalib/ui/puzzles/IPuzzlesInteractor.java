package com.grapecity.debugrank.javalib.ui.puzzles;

import com.grapecity.debugrank.javalib.entities.AggregatedBugsPoints;
import com.grapecity.debugrank.javalib.entities.CompletedPuzzle;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.ui.base.IMvpInteractor;

import java.util.List;
import java.util.Map;

import rx.Observable;


/**
 * Created by chrisr on 2/29/2016.
 */
public interface IPuzzlesInteractor  extends IMvpInteractor
{
    Observable<List<Puzzle>> loadPuzzles(ProgrammingLanguage language);

    Observable<AggregatedBugsPoints> loadAggregatedBugsPoints(ProgrammingLanguage programmingLanguage);

    Observable<Map<String, CompletedPuzzle>> loadCompletedPuzzles(ProgrammingLanguage language);
}
