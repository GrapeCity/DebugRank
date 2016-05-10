package com.grapecity.debugrank.javalib.ui.solve.result;

import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.HackerRankCompileParams;
import com.grapecity.debugrank.javalib.entities.HackerRankResult;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.entities.TestCaseResult;
import com.grapecity.debugrank.javalib.services.compile.ICompilerApi;
import com.grapecity.debugrank.javalib.services.repo.IDataRepository;
import com.grapecity.debugrank.javalib.services.repo.IUserRepository;
import com.grapecity.debugrank.javalib.services.web.IWebClient;
import com.grapecity.debugrank.javalib.ui.base.BaseRepositoryInteractor;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by chrisr on 2/29/2016.
 */
public class SolveResultInteractorImpl extends BaseRepositoryInteractor implements ISolveResultInteractor
{
    private final IWebClient<HackerRankResult, HackerRankCompileParams> webClient;
    private final ICompilerApi compilerApi;

    public SolveResultInteractorImpl(IDataRepository dataRepository, IUserRepository userRepository, IWebClient<HackerRankResult, HackerRankCompileParams> webClient, ICompilerApi compilerApi)
    {
        super(dataRepository, userRepository);

        this.webClient = webClient;
        this.compilerApi = compilerApi;
    }

    @Override
    public Observable<List<TestCaseResult>> compileCode(ProgrammingLanguage language, final Puzzle puzzle, List<CodeLine> codeLines)
    {
        //TODO: these should run on background thread as well?
        String uriSafeCode = this.compilerApi.getUriSafeCode(codeLines);
        String uriSafeTestCases = this.compilerApi.getUriSafeTestCases(puzzle);

        HackerRankCompileParams params = new HackerRankCompileParams(uriSafeCode, language, uriSafeTestCases);

        return webClient.enqueue(params).map(new Func1<HackerRankResult, List<TestCaseResult>>()
        {
            @Override
            public List<TestCaseResult> call(HackerRankResult hackerRankResult)
            {
                return compilerApi.getTestCaseResults(hackerRankResult, puzzle);
            }
        });
    }

    @Override
    public int getPassedTestCases(List<TestCaseResult> result)
    {
        int numPassed = 0;

        for (TestCaseResult testCaseResult : result)
        {
            numPassed += testCaseResult.passed ? 1 : 0;
        }

        return numPassed;
    }

    @Override
    public boolean didCodePassAllTestCases(int numberPassedTestCases, Puzzle puzzle)
    {
        return numberPassedTestCases == puzzle.answers.length;
    }
}
