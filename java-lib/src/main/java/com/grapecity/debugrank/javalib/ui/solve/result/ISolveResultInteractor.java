package com.grapecity.debugrank.javalib.ui.solve.result;

import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.CompletedPuzzle;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.entities.TestCaseResult;
import com.grapecity.debugrank.javalib.ui.base.IMvpInteractor;

import java.util.List;

import rx.Observable;

/**
 * Created by chrisr on 2/29/2016.
 */
public interface ISolveResultInteractor extends IMvpInteractor
{
    Observable<List<TestCaseResult>> compileCode(ProgrammingLanguage language, Puzzle puzzle, List<CodeLine> codeLines);

    Observable<CompletedPuzzle> saveCompletedPuzzle(ProgrammingLanguage programmingLanguage, Puzzle puzzle);

    int getPassedTestCases(List<TestCaseResult> result);

    boolean didCodePassAllTestCases(int numberPassedTestCases, Puzzle puzzle);
}
