package com.grapecity.debugrank.ui.solve;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.grapecity.debugrank.javalib.entities.AggregatedBugsPoints;
import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.CompletedPuzzle;
import com.grapecity.debugrank.javalib.entities.TestCaseResult;
import com.grapecity.debugrank.javalib.ui.solve.ISolvePresenter;
import com.grapecity.debugrank.javalib.ui.solve.ISolveView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import com.grapecity.debugrank.MyApp;
import com.grapecity.debugrank.R;
import com.grapecity.debugrank.services.image.IImageLoadingService;
import com.grapecity.debugrank.ui.misc.NonSwipeableViewPager;
import com.grapecity.debugrank.ui.base.BaseActivity;
import com.grapecity.debugrank.ui.languages.LanguagesActivity;
import com.grapecity.debugrank.ui.misc.like.LikeButtonView;
import com.grapecity.debugrank.ui.misc.timer.ITimerCompletedListener;
import com.grapecity.debugrank.ui.solve.code.SolveCodeFragment;
import com.grapecity.debugrank.ui.solve.result.SolveResultFragment;

public class SolveActivity extends BaseActivity implements ISolveView, ITimerCompletedListener
{
    final Activity activity = this;

    @Bind(R.id.tabViewPageParent)
    View tabViewPageParent;

    @Bind(R.id.tabs)
    TabLayout tabLayout;

    @Bind(R.id.viewpager)
    NonSwipeableViewPager viewPager;

    @Inject
    ISolvePresenter solvePresenter;

    @Inject
    IImageLoadingService imageLoadingService;

    SolveViewPagerAdapter adapter;

    boolean codeLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ((MyApp) getApplication()).getSolveComponent().inject(this);

        setImageDrawable(getFab(), R.drawable.ic_file_upload_white_24dp);

        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                uploadCode();
            }
        });

        hideActionBar();

        aggregatedBugPointsLoaded(new AggregatedBugsPoints(puzzle.bugs, puzzle.points));

        timerTextView.setTimeInSeconds(puzzle.time);
        timerTextView.setOnCompletedListener(this);

        attachView();

        setupViewPager();

        //hide fab till code loaded
        hideFab();
    }

    @Override
    protected void dataLoadedSuccessfully()
    {
        super.dataLoadedSuccessfully();

        showFab();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        if(timerTextView != null)
        {
            timerTextView.stopTimer();
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        if(timerTextView != null)
        {
            timerTextView.stopTimer();
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        //if resuming the activity and the code was previously loaded
        if(codeLoaded)
        {
            if(timerTextView != null)
            {
                timerTextView.startTimer();
            }
        }
    }

    @Override
    protected void attachView()
    {
        solvePresenter.attachView(this);
    }

    private void setupViewPager()
    {
        Resources resources = getResources();

        SolveCodeFragment solveCodeFragment = new SolveCodeFragment();
        SolveResultFragment solveResultFragment = new SolveResultFragment();

        adapter = new SolveViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(solveCodeFragment, resources.getString(R.string.code));
        adapter.addFragment(solveResultFragment, resources.getString(R.string.result));

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        solvePresenter.attachView(solveCodeFragment);
        solvePresenter.attachView(solveResultFragment);
    }

    @Override
    protected int getContentAreaLayoutId()
    {
        return R.layout.content_solve;
    }

    @Override
    public void solveCodeLoaded()
    {
        codeLoaded = true;
        timerTextView.startTimer();
        super.dataLoadedSuccessfully();

        //don't allow code to be refresh anymore
        swipeRefreshLayout.setEnabled(false);

        showFab();
    }

    @Override
    public void unableToLoadSolveCode()
    {
        super.dataLoadedFailure();
    }

    @Override
    public void unableToUploadAndCompileCode()
    {
        Toast.makeText(this, R.string.internet_error, Toast.LENGTH_LONG).show();

        enableFabForUpload();

        updateResultTabTitle(getResources().getString(R.string.internet_no));
    }

    @Override
    public void codeCompiling()
    {
        //set FAB to uploading
        setImageDrawable(getFab(), R.drawable.ic_sync_white_24dp);
        getFab().setEnabled(false);

        //set tab title to uploading
        updateResultTabTitle(getResources().getString(R.string.uploading));
    }

    @Override
    public void codeCompiled(List<TestCaseResult> result, boolean passed, int numberPassedTestCases)
    {
        //compile error
        if(result != null && !passed && numberPassedTestCases == 0 && result.size() > 0 && result.get(0).isCompileError())
        {
            updateResultTabTitle(getResources().getString(R.string.compile_error));
        }
        //valid code
        else
        {
            updateResultTabTitle(String.format(getResources().getString(R.string.passed_test_cases), numberPassedTestCases, puzzle.answers.length));
        }

        if (passed || timerTextView.isCompleted())
        {
            if (!passed && timerTextView.isCompleted())
            {
                showDialog(false);
            }

            timerTextView.stopTimer();
            hideFab();
        } else
        {
            enableFabForUpload();
        }
    }

    private void enableFabForUpload()
    {
        setImageDrawable(getFab(), R.drawable.ic_file_upload_white_24dp);
        getFab().setEnabled(true);
    }

    @Override
    public void puzzleSolved(CompletedPuzzle result)
    {
        showDialog(true);
    }

    private void showDialog(boolean passed)
    {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this);

        LikeButtonView likeView = null;

        if (passed)
        {
            likeView = new LikeButtonView(this);

            builder
                    .title(String.format(getResources().getString(R.string.points).replaceAll("\n", " "), puzzle.points))
                    .customView(likeView, false);
        } else
        {
            builder
                    .title(getResources().getString(R.string.out_of_time))
                    .customView(R.layout.view_time_out, false);
        }

        builder
                .cancelable(false)
                .positiveText(R.string.ok)
                .onPositive(new MaterialDialog.SingleButtonCallback()
                {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which)
                    {
                        Intent intent = getIntent(LanguagesActivity.class);
                        activity.startActivity(intent);
                    }
                })
                .show();

        if (passed)
        {
            //automatically trigger the like animation
            likeView.performClick();
            likeView.setEnabled(false);
        }
    }

    private void uploadCode()
    {
        //called whenever FAB is clicked to perform an upload, or the time runs out so we need to automatically upload
        solvePresenter.uploadCode();
    }

    private void updateResultTabTitle(String newTitle)
    {
        tabLayout.getTabAt(1).setText(newTitle);
    }

    @Override
    public void timeRanOut()
    {
        //called when time runs out
        uploadCode();
    }

    @Override
    public void beginEditing(CodeLine codeLine)
    {
        //hide fab when editing code
        hideFab();
    }

    @Override
    public void finishEditing(CodeLine codeLine)
    {
        //show fab when done editing code
        showFab();
    }

    private void setImageDrawable(ImageView imageView, @DrawableRes int resourceId)
    {
        imageLoadingService.setDrawable(imageView, resourceId);
    }

    @Override
    public void onRefresh()
    {
        if(!codeLoaded)
        {
            solvePresenter.loadCode();
        }
    }
}