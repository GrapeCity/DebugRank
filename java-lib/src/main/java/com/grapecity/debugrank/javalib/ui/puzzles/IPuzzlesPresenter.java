package com.grapecity.debugrank.javalib.ui.puzzles;


import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.ui.base.IMvpPresenter;

/**
 * Created by chrisr on 2/29/2016.
 */
public interface IPuzzlesPresenter  extends IMvpPresenter<IPuzzlesView>
{
    void loadPuzzles();

    void loadAggregatedBugPoints(ProgrammingLanguage language);

    void loadCompletedPuzzles(ProgrammingLanguage language);

    void puzzleClicked(Puzzle puzzle);
}
