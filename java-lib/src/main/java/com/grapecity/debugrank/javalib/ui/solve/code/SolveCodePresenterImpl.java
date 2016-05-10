package com.grapecity.debugrank.javalib.ui.solve.code;

import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.services.thread.IAppThreads;
import com.grapecity.debugrank.javalib.ui.base.BaseMvpPresenter;
import com.grapecity.debugrank.javalib.ui.base.IMvpView;
import com.grapecity.debugrank.javalib.ui.solve.BaseSolveChildPresenter;
import com.grapecity.debugrank.javalib.ui.solve.ISolveView;
import com.grapecity.debugrank.javalib.util.ObserverAdapter;

import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;


/**
 * Created by chrisr on 2/29/2016.
 */
public class SolveCodePresenterImpl<T> extends BaseSolveChildPresenter<ISolveCodeView, ISolveCodeInteractor<T>> implements ISolveCodePresenter<T>
{
    public SolveCodePresenterImpl(IAppThreads appThreads, ISolveCodeInteractor mvpInteractor)
    {
        super(appThreads, mvpInteractor);
    }

    @Override
    public void loadCode()
    {
        this.mvpInteractor.loadCodeFile(mvpView.getLanguage(), mvpView.getPuzzle())
                .subscribeOn(appThreads.newThread())
                .observeOn(appThreads.mainThread())
                .subscribe(new ObserverAdapter<List<CodeLine>>(){
                    @Override
                    public void onNext(List<CodeLine> codeLines)
                    {
                        mvpView.codeLoaded(codeLines);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        parentView.unableToLoadSolveCode();
                    }
                });
    }

    @Override
    public T getSyntaxHighlightedText(CodeLine codeLine)
    {
        return this.mvpInteractor.getSyntaxHighlightedText(this.mvpView.getLanguage(), codeLine);
    }

    @Override
    public void beginEditing(CodeLine codeLine)
    {
        parentView.beginEditing(codeLine);
    }

    @Override
    public void finishEditing(CodeLine codeLine)
    {
        parentView.finishEditing(codeLine);

        this.mvpInteractor.refreshSyntaxHighlightedTextCache(this.mvpView.getLanguage(), codeLine)
                .subscribeOn(appThreads.newThread())
                .observeOn(appThreads.mainThread())
                .subscribe(new ObserverAdapter<T>(){
                    @Override
                    public void onNext(T displayValue)
                    {
                        mvpView.syntaxHighlightedTextRefreshed(displayValue);
                    }
                });
    }


}