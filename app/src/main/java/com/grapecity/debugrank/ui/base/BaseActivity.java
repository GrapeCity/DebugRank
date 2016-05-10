package com.grapecity.debugrank.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.grapecity.debugrank.R;
import com.grapecity.debugrank.constants.IntentExtras;
import com.grapecity.debugrank.ui.misc.timer.TimerTextView;
import com.grapecity.debugrank.ui.solve.SolveActivity;

import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.entities.AggregatedBugsPoints;
import com.grapecity.debugrank.javalib.ui.base.IAggregatedBugPointsListener;
import com.grapecity.debugrank.javalib.ui.base.IGetLanguage;
import com.grapecity.debugrank.javalib.ui.base.IGetPuzzle;


/**
 * Created by chrisr on 1/27/2016.
 */
public abstract class BaseActivity extends AppCompatActivity implements IAggregatedBugPointsListener, IGetLanguage, IGetPuzzle, SwipeRefreshLayout.OnRefreshListener
{
    @Nullable
    @InjectExtra
    protected ProgrammingLanguage language;

    @Nullable
    @InjectExtra
    protected Puzzle puzzle;

    @Bind(R.id.fab)
    protected FloatingActionButton fab;

    protected Toolbar toolbar;

    @Bind(R.id.bugs)
    protected TextView bugs;

    @Bind(R.id.points)
    protected TextView points;

    @Bind(R.id.minutes)
    protected TimerTextView timerTextView;

    @Bind(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout swipeRefreshLayout;

    //view that is shown when data successfully loaded
    protected ViewGroup contentContainer;

    @Bind(R.id.loadingProgressBar)
    protected ProgressBar loadingProgressBar;

    @Bind(R.id.noDataLayout)
    protected View noDataLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity);

        //setup native android way so that butterknife can bind children
        contentContainer = (ViewGroup) findViewById(R.id.contentContainer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        View.inflate(this, getContentAreaLayoutId(), contentContainer);
        View.inflate(this, R.layout.view_toolbar, toolbar);

        ButterKnife.bind(this);

        Dart.inject(this);

        setSupportActionBar(toolbar);

        swipeRefreshLayout.setOnRefreshListener(this);
    }

    protected abstract void attachView();

    protected abstract
    @LayoutRes
    int getContentAreaLayoutId();

    protected void hideActionBar()
    {
        setActionBarTitle(null);
    }

    protected void setActionBarTitle(String title)
    {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
        {
            if (title != null)
            {
                actionBar.setTitle(title);
            } else
            {
                actionBar.setDisplayShowTitleEnabled(false);
            }
        }
    }

    @Override
    public void aggregatedBugPointsLoaded(AggregatedBugsPoints aggregatedBugsPoints)
    {
        bugs.setText(String.valueOf(aggregatedBugsPoints.bugs));
        points.setText(String.valueOf(aggregatedBugsPoints.points));

        if (!(this instanceof SolveActivity))
        {
            timerTextView.setVisibility(View.GONE);
        }
    }

    protected void hideFab()
    {
        fab.setEnabled(false);
        fab.setVisibility(View.GONE);
    }

    protected void showFab()
    {
        fab.setEnabled(true);
        fab.setVisibility(View.VISIBLE);
    }

    protected void dataLoadedSuccessfully()
    {
        swipeRefreshLayout.setRefreshing(false);

        loadingProgressBar.setVisibility(View.GONE);
        noDataLayout.setVisibility(View.GONE);
        contentContainer.setVisibility(View.VISIBLE);
    }

    protected void dataLoadedFailure()
    {
        swipeRefreshLayout.setRefreshing(false);

        loadingProgressBar.setVisibility(View.GONE);
        noDataLayout.setVisibility(View.VISIBLE);
        contentContainer.setVisibility(View.GONE);
    }

    public FloatingActionButton getFab()
    {
        return fab;
    }

    protected Intent getIntent(Class<?> activityClass)
    {
        return getIntent(activityClass, null, null);
    }

    protected Intent getIntent(Class<?> activityClass, ProgrammingLanguage language)
    {
        return getIntent(activityClass, language, null);
    }

    protected Intent getIntent(Class<?> activityClass, ProgrammingLanguage language, Puzzle puzzle)
    {
        Intent intent = new Intent(this, activityClass);

        if (language != null)
        {
            intent.putExtra(IntentExtras.EXTRA_LANGUAGE_KEY, language);
        }

        if (puzzle != null)
        {
            intent.putExtra(IntentExtras.EXTRA_PUZZLE_KEY, puzzle);
        }

        return intent;
    }

    @Override
    public ProgrammingLanguage getLanguage()
    {
        return language;
    }

    @Override
    public Puzzle getPuzzle()
    {
        return puzzle;
    }
}