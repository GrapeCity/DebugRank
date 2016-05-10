package com.grapecity.debugrank.ui.languages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.ui.languages.ILanguagesPresenter;
import com.grapecity.debugrank.javalib.ui.languages.ILanguagesView;

import java.util.List;

import javax.inject.Inject;

import com.grapecity.debugrank.MyApp;
import com.grapecity.debugrank.services.image.IImageLoadingService;
import com.grapecity.debugrank.ui.base.BaseRecyclerViewActivity;
import com.grapecity.debugrank.ui.puzzles.PuzzlesActivity;

public class LanguagesActivity extends BaseRecyclerViewActivity<LanguagesAdapter> implements ILanguagesView, View.OnClickListener
{
    @Inject
    ILanguagesPresenter languagesPresenter;

    @Inject
    IImageLoadingService imageLoadingService;

    private ProgrammingLanguage selectedLanguage;
    private View selectedView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ((MyApp) getApplication()).getLanguagesComponent().inject(this);

        attachView();
    }

    @Override
    protected void attachView()
    {
        super.attachView();

        languagesPresenter.attachView(this);
    }

    @Override
    protected LanguagesAdapter getRecyclerViewAdapter()
    {
        return new LanguagesAdapter(this, imageLoadingService);
    }

    @Override
    public void programmingLanguagesLoaded(List<ProgrammingLanguage> programmingLanguageList)
    {
        adapter.setLanguages(programmingLanguageList);

        super.dataLoadedSuccessfully();
    }

    @Override
    public void unableToLoadLanguages()
    {
        super.dataLoadedFailure();
    }

    @Override
    public void navigateToPuzzleScreen()
    {
        Intent intent = getIntent(PuzzlesActivity.class, selectedLanguage);
        startActivity(intent);
    }

    @Override
    public void onClick(View v)
    {
        this.selectedLanguage = (ProgrammingLanguage) v.getTag();
        this.selectedView = v;

        this.languagesPresenter.programmingLanguageClicked(this.selectedLanguage);
    }

    @Override
    public void onRefresh()
    {
        languagesPresenter.loadProgrammingLanguages();
    }
}