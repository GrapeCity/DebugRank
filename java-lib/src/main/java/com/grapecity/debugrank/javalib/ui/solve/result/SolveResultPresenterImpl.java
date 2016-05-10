package com.grapecity.debugrank.javalib.ui.solve.result;

import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.CompletedPuzzle;
import com.grapecity.debugrank.javalib.entities.TestCaseResult;
import com.grapecity.debugrank.javalib.services.thread.IAppThreads;
import com.grapecity.debugrank.javalib.ui.base.BaseMvpPresenter;
import com.grapecity.debugrank.javalib.ui.solve.BaseSolveChildPresenter;
import com.grapecity.debugrank.javalib.ui.solve.ISolveView;
import com.grapecity.debugrank.javalib.util.ObserverAdapter;

import java.util.List;

import rx.schedulers.Schedulers;

/**
 * Created by chrisr on 2/29/2016.
 */
public class SolveResultPresenterImpl extends BaseSolveChildPresenter<ISolveResultView, ISolveResultInteractor> implements ISolveResultPresenter
{
    public SolveResultPresenterImpl(IAppThreads appThreads, ISolveResultInteractor mvpInteractor)
    {
        super(appThreads, mvpInteractor);
    }

    @Override
    public void compileCode(List<CodeLine> codeLines)
    {
        super.mvpView.codeCompiling();

        this.mvpInteractor.compileCode(super.mvpView.getLanguage(), super.mvpView.getPuzzle(), codeLines)
                .subscribeOn(appThreads.newThread())
                .observeOn(appThreads.mainThread())
                .subscribe(new ObserverAdapter<List<TestCaseResult>>()
                {
                    @Override
                    public void onNext(List<TestCaseResult> result)
                    {
                        processTestCaseResults(result);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        parentView.unableToUploadAndCompileCode();
                    }
                });
    }

    private void processTestCaseResults(List<TestCaseResult> result)
    {
        int numberPassedTestCases = mvpInteractor.getPassedTestCases(result);

        boolean passed = mvpInteractor.didCodePassAllTestCases(numberPassedTestCases, mvpView.getPuzzle());

        parentView.codeCompiled(result, passed, numberPassedTestCases);
        mvpView.codeCompiled(result, passed, numberPassedTestCases);

        if (passed)
        {
            mvpInteractor.saveCompletedPuzzle(mvpView.getLanguage(), mvpView.getPuzzle())
                    .subscribeOn(appThreads.newThread())
                    .observeOn(appThreads.mainThread())
                    .subscribe(new ObserverAdapter<CompletedPuzzle>()
                    {
                        @Override
                        public void onNext(CompletedPuzzle result)
                        {
                            parentView.puzzleSolved(result);
                            mvpView.puzzleSolved(result);
                        }
                    });
        }
    }
}
