package com.grapecity.debugrank.javalib.ui.puzzles;

import com.grapecity.debugrank.javalib.entities.AggregatedBugsPoints;
import com.grapecity.debugrank.javalib.entities.CompletedPuzzle;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.services.thread.IAppThreads;
import com.grapecity.debugrank.javalib.ui.base.BaseMvpPresenter;
import com.grapecity.debugrank.javalib.util.ObserverAdapter;

import java.util.List;
import java.util.Map;

import rx.schedulers.Schedulers;


/**
 * Created by chrisr on 2/29/2016.
 */
public class PuzzlesPresenterImpl extends BaseMvpPresenter<IPuzzlesView, IPuzzlesInteractor> implements IPuzzlesPresenter
{
    public PuzzlesPresenterImpl(IAppThreads appThreads, IPuzzlesInteractor puzzlesInteractor)
    {
        super(appThreads, puzzlesInteractor);
    }

    @Override
    public void attachView(IPuzzlesView mvpView)
    {
        super.attachView(mvpView);

        loadPuzzles();
        loadAggregatedBugPoints(mvpView.getLanguage());
        loadCompletedPuzzles(mvpView.getLanguage());
    }

    @Override
    public void loadPuzzles()
    {
        this.mvpInteractor.loadPuzzles(mvpView.getLanguage())
                .subscribeOn(appThreads.newThread())
                .observeOn(appThreads.mainThread())
                .subscribe(new ObserverAdapter<List<Puzzle>>()
                {
                    @Override
                    public void onNext(List<Puzzle> puzzles)
                    {
                        mvpView.puzzlesLoaded(puzzles);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        mvpView.unableToLoadPuzzles();
                    }
                });
    }

    @Override
    public void loadAggregatedBugPoints(ProgrammingLanguage language)
    {
        this.mvpInteractor.loadAggregatedBugsPoints(mvpView.getLanguage())
                .subscribeOn(appThreads.newThread())
                .observeOn(appThreads.mainThread())
                .subscribe(new ObserverAdapter<AggregatedBugsPoints>()
                {
                    @Override
                    public void onNext(AggregatedBugsPoints aggregatedBugsPoints)
                    {
                        mvpView.aggregatedBugPointsLoaded(aggregatedBugsPoints);
                    }
                });
    }

    @Override
    public void loadCompletedPuzzles(ProgrammingLanguage language)
    {
        this.mvpInteractor.loadCompletedPuzzles(mvpView.getLanguage())
                .subscribeOn(appThreads.newThread())
                .observeOn(appThreads.mainThread())
                .subscribe(new ObserverAdapter<Map<String, CompletedPuzzle>>()
                {
                    @Override
                    public void onNext(Map<String, CompletedPuzzle> completedPuzzleMap)
                    {
                        mvpView.completedPuzzlesLoaded(completedPuzzleMap);
                    }
                });
    }

    @Override
    public void puzzleClicked(Puzzle puzzle)
    {
        super.mvpView.navigateToSolveScreen();
    }
}
