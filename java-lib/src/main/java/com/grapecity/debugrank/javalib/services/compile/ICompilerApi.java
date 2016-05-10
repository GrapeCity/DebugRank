package com.grapecity.debugrank.javalib.services.compile;

import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.HackerRankResult;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.entities.TestCaseResult;

import java.util.List;



/**
 * Created by chrisr on 2/29/2016.
 */
public interface ICompilerApi
{
    String getUriSafeTestCases(Puzzle puzzle);

    String getUriSafeCode(List<CodeLine> codeLines);

    List<TestCaseResult> getTestCaseResults(HackerRankResult hackerRankResult, Puzzle puzzle);
}
