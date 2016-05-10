package com.grapecity.debugrank.javalib.ui.languages;

import com.grapecity.debugrank.javalib.entities.AggregatedBugsPoints;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.services.thread.IAppThreads;
import com.grapecity.debugrank.javalib.ui.base.BaseMvpPresenter;
import com.grapecity.debugrank.javalib.util.ObserverAdapter;

import java.util.List;

import rx.schedulers.Schedulers;


/**
 * Created by chrisr on 2/29/2016.
 */
public class LanguagesPresenterImpl extends BaseMvpPresenter<ILanguagesView, ILanguagesInteractor> implements ILanguagesPresenter
{
    public LanguagesPresenterImpl(IAppThreads appThreads, ILanguagesInteractor languagesInteractor)
    {
        super(appThreads, languagesInteractor);
    }

    @Override
    public void attachView(ILanguagesView mvpView)
    {
        super.attachView(mvpView);

        loadProgrammingLanguages();
        loadAggregatedBugPoints();
    }

    @Override
    public void loadProgrammingLanguages()
    {
        this.mvpInteractor.loadProgrammingLanguages()
                .subscribeOn(appThreads.newThread())
                .observeOn(appThreads.mainThread())
                .subscribe(new ObserverAdapter<List<ProgrammingLanguage>>()
                {
                    @Override
                    public void onNext(List<ProgrammingLanguage> programmingLanguages)
                    {
                        mvpView.programmingLanguagesLoaded(programmingLanguages);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        mvpView.unableToLoadLanguages();
                    }
                });
    }

    @Override
    public void loadAggregatedBugPoints()
    {
        this.mvpInteractor.loadAggregatedBugsPoints()
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
    public void programmingLanguageClicked(ProgrammingLanguage programmingLanguage)
    {
        this.mvpView.navigateToPuzzleScreen();
    }
}
