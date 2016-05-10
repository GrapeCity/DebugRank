package com.grapecity.debugrank.ui.puzzles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.grapecity.debugrank.javalib.entities.CompletedPuzzle;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.ui.puzzles.IPuzzlesPresenter;
import com.grapecity.debugrank.javalib.ui.puzzles.IPuzzlesView;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.grapecity.debugrank.MyApp;
import com.grapecity.debugrank.services.image.IImageLoadingService;
import com.grapecity.debugrank.ui.base.BaseRecyclerViewActivity;
import com.grapecity.debugrank.ui.solve.SolveActivity;

public class PuzzlesActivity extends BaseRecyclerViewActivity<PuzzlesAdapter> implements IPuzzlesView, View.OnClickListener
{
    @Inject
    IPuzzlesPresenter puzzlesPresenter;

    @Inject
    IImageLoadingService imageLoadingService;

    private Puzzle selectedPuzzle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ((MyApp) getApplication()).getPuzzlesComponent().inject(this);

        setActionBarTitle(language.name);

        attachView();
    }

    @Override
    protected void attachView()
    {
        super.attachView();

        puzzlesPresenter.attachView(this);
    }

    @Override
    protected PuzzlesAdapter getRecyclerViewAdapter()
    {
        return new PuzzlesAdapter(this, getResources(), language, imageLoadingService);
    }

    @Override
    public void puzzlesLoaded(List<Puzzle> puzzleList)
    {
        adapter.setPuzzles(puzzleList);

        super.dataLoadedSuccessfully();
    }

    @Override
    public void unableToLoadPuzzles()
    {
        super.dataLoadedFailure();
    }

    @Override
    public void completedPuzzlesLoaded(Map<String, CompletedPuzzle> completedPuzzleMap)
    {
        adapter.setCompletedPuzzleMap(completedPuzzleMap);
    }

    @Override
    public void navigateToSolveScreen()
    {
        Intent intent = getIntent(SolveActivity.class, language, selectedPuzzle);
        startActivity(intent);
    }

    @Override
    public void onClick(View v)
    {
        this.selectedPuzzle = (Puzzle) v.getTag();

        this.puzzlesPresenter.puzzleClicked(this.selectedPuzzle);
    }

    @Override
    public void onRefresh()
    {
        puzzlesPresenter.loadPuzzles();
    }
}