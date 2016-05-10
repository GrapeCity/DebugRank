package com.grapecity.debugrank.javalib.ui.solve;

import com.grapecity.debugrank.javalib.entities.CompletedPuzzle;
import com.grapecity.debugrank.javalib.entities.TestCaseResult;
import com.grapecity.debugrank.javalib.ui.base.IGetLanguage;
import com.grapecity.debugrank.javalib.ui.base.IGetPuzzle;
import com.grapecity.debugrank.javalib.ui.base.IMvpView;

import java.util.List;

/**
 * Created by chrisr on 3/31/2016.
 */
public interface ISolveChildView extends IGetLanguage, IGetPuzzle, IMvpView
{
    void codeCompiling();

    void codeCompiled(List<TestCaseResult> result, boolean passed, int numberPassedTestCases);

    void puzzleSolved(CompletedPuzzle result);
}
