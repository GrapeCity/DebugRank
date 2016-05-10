package com.grapecity.debugrank.javalib.ui.solve.result;

import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.CompletedPuzzle;
import com.grapecity.debugrank.javalib.entities.HackerRankCompileParams;
import com.grapecity.debugrank.javalib.entities.HackerRankResult;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.entities.TestCaseResult;
import com.grapecity.debugrank.javalib.services.compile.ICompilerApi;
import com.grapecity.debugrank.javalib.services.repo.IDataRepository;
import com.grapecity.debugrank.javalib.services.repo.IUserRepository;
import com.grapecity.debugrank.javalib.services.web.IWebClient;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by chrisripple on 4/20/16.
 */
public class SolveResultInteractorImplTests
{
    SolveResultInteractorImpl interactor;

    IDataRepository dataRepository;
    IUserRepository userRepository;
    IWebClient<HackerRankResult, HackerRankCompileParams> webClient;
    ICompilerApi compilerApi;

    @Before
    public void init()
    {
        dataRepository = mock(IDataRepository.class);
        userRepository = mock(IUserRepository.class);

        webClient = mock(IWebClient.class);

        doReturn(Observable.just(new HackerRankResult())).when(webClient).enqueue((HackerRankCompileParams) Mockito.any());

        compilerApi = mock(ICompilerApi.class);

        doReturn(null).when(compilerApi).getUriSafeCode(null);
        doReturn(null).when(compilerApi).getUriSafeTestCases(null);
        doReturn(new ArrayList<TestCaseResult>()).when(compilerApi).getTestCaseResults(null, null);

        interactor = new SolveResultInteractorImpl(dataRepository, userRepository, webClient, compilerApi);
    }

    @Test
    public void testCompileCode()
    {
        interactor.compileCode(null, null, null);

        verify(compilerApi, times(1)).getUriSafeCode(null);
        verify(compilerApi, times(1)).getUriSafeTestCases(null);
    }

    @Test
    public void testGetPassedTestCases()
    {
        List<TestCaseResult> list = new ArrayList<>();

        list.add(new TestCaseResult(1, new String[]{"input"}, "output", "output", "message"));
        list.add(new TestCaseResult(2, new String[]{"input"}, "output", "expectedOutput", "message"));

        int passedNum = interactor.getPassedTestCases(list);

        assertEquals(1, passedNum);
    }

    @Test
    public void testDidCodePassAllTestCasesSuccess()
    {
        Puzzle puzzle = new Puzzle("sadfas", "345345", 0, 0, 0, new String[0], new String[]{"answer"});

        boolean passed = interactor.didCodePassAllTestCases(1, puzzle);

        assertEquals(true, passed);
    }

    @Test
    public void testDidCodePassAllTestCasesFailed()
    {
        Puzzle puzzle = new Puzzle("sadfas", "345345", 0, 0, 0, new String[0], new String[]{"answer", "answer 2"});

        boolean passed = interactor.didCodePassAllTestCases(1, puzzle);

        assertEquals(false, passed);
    }
}
