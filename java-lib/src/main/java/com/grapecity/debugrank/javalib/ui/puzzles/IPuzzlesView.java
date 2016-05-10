package com.grapecity.debugrank.javalib.ui.puzzles;

import com.grapecity.debugrank.javalib.entities.CompletedPuzzle;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.ui.base.IGetLanguage;
import com.grapecity.debugrank.javalib.ui.base.IMvpView;

import java.util.List;
import java.util.Map;


/**
 * Created by chrisr on 2/29/2016.
 */
public interface IPuzzlesView extends IGetLanguage, IMvpView
{
    void puzzlesLoaded(List<Puzzle> puzzleList);

    void unableToLoadPuzzles();

    void completedPuzzlesLoaded(Map<String, CompletedPuzzle> completedPuzzleMap);

    void navigateToSolveScreen();
}
