package com.grapecity.debugrank.javalib.services.compile;

import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.HackerRankResult;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.entities.Result;
import com.grapecity.debugrank.javalib.entities.TestCaseResult;
import com.grapecity.debugrank.javalib.services.log.ILogger;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;

/**
 * Created by chrisripple on 4/17/16.
 */
public class HackerRankCompilerApiTests
{
    ICompilerApi api;

    @Before
    public void init()
    {
        ILogger logger = mock(ILogger.class);
        api = new HackerRankCompilerApi(logger);
    }

    @Test
    public void getUriSafeTestCases()
    {
        final String EXPECTED = "[\"testcase\"]";

        Puzzle puzzle = new Puzzle("basic_operator", "Basic Operator", 1, 2, 3, new String[]{"testcase"}, new String[]{"answer"});

        String uriSafe = api.getUriSafeTestCases(puzzle);

        assertEquals(EXPECTED, uriSafe);
    }

    @Test
    public void getUriSafeCode()
    {
        final String EXPECTED = "code line 1\ncode line 2\ncode line 3\n";

        List<CodeLine> list = new ArrayList<>();

        list.add(new CodeLine(1, "code line 1"));
        list.add(new CodeLine(2, "code line 2"));
        list.add(new CodeLine(3, "code line 3"));

        String uriSafe = api.getUriSafeCode(list);

        assertEquals(EXPECTED, uriSafe);
    }

    @Test
    public void getTestCaseResults()
    {
        HackerRankResult hackerRankResult = new HackerRankResult();

        Result result = new Result();

        result.setStdout(new String[]{"answer"});
        result.setStderr(new String[]{"hello msg"});

        hackerRankResult.setResult(result);

        Puzzle puzzle = new Puzzle("basic_operator", "Basic Operator", 1, 2, 3, new String[]{"testcase"}, new String[]{"answer"});

        List<TestCaseResult> testCaseResults = api.getTestCaseResults(hackerRankResult, puzzle);

        assertEquals(true, testCaseResults.get(0).passed);
        assertEquals("answer", testCaseResults.get(0).output);
        assertEquals("hello msg", testCaseResults.get(0).message);
    }
}
