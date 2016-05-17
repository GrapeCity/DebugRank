package com.grapecity.debugrank.javalib.ui.solve.code;

import com.grapecity.debugrank.javalib.entities.AggregatedBugsPoints;
import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.services.html.IHtmlToDisplay;
import com.grapecity.debugrank.javalib.services.repo.IDataRepository;
import com.grapecity.debugrank.javalib.services.repo.IUserRepository;
import com.grapecity.debugrank.javalib.services.syntax.ISyntaxHighlighter;
import com.grapecity.debugrank.javalib.ui.base.BaseRepositoryInteractor;
import com.grapecity.debugrank.javalib.util.ObserverAdapter;
import com.sun.org.apache.bcel.internal.classfile.Code;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;


/**
 * Created by chrisr on 2/29/2016.
 */
public class SolveCodeInteractorImpl<T> extends BaseRepositoryInteractor implements ISolveCodeInteractor<T>
{
    private final ISyntaxHighlighter syntaxHighlighter;
    private final IHtmlToDisplay<T> htmlToDisplay;

    private Map<Integer, T> highlightedCache;

    public SolveCodeInteractorImpl(IDataRepository dataRepository, IUserRepository userRepository, ISyntaxHighlighter syntaxHighlighter, IHtmlToDisplay<T> htmlToDisplay)
    {
        super(dataRepository, userRepository);

        this.syntaxHighlighter = syntaxHighlighter;
        this.htmlToDisplay = htmlToDisplay;
    }

    @Override
    public Observable<List<CodeLine>> loadCodeFile(final ProgrammingLanguage language, Puzzle puzzle)
    {
        return super.loadCodeFile(language, puzzle).doOnNext(new Action1<List<CodeLine>>()
        {
            @Override
            public void call(List<CodeLine> codeLines)
            {
                //fill cache of highlighted code text
                highlightedCache = new HashMap<>(codeLines.size());

                for(CodeLine codeLine : codeLines)
                {
                    highlightedCache.put(codeLine.lineNumber, getSyntaxHighlighted(language.file, codeLine.getCodeText()));
                }
            }
        });
    }

    @Override
    public T getSyntaxHighlightedText(ProgrammingLanguage language, CodeLine codeLine)
    {
        if(this.highlightedCache.containsKey(codeLine.lineNumber))
        {
            return this.highlightedCache.get(codeLine.lineNumber);
        }

        return htmlToDisplay.convertHtmlToDisplayType(null, codeLine.getCodeText());
    }

    @Override
    public Observable<T> refreshSyntaxHighlightedTextCache(final ProgrammingLanguage language, CodeLine codeLine)
    {
        //remove from cache so out of date code is never displayed
        this.highlightedCache.remove(codeLine.lineNumber);

        return Observable.just(codeLine).map(new Func1<CodeLine, T>()
        {
            @Override
            public T call(CodeLine codeLine)
            {
                T dispayValue = getSyntaxHighlighted(language.file, codeLine.getCodeText());

                highlightedCache.put(codeLine.lineNumber, dispayValue);

                return dispayValue;
            }
        });
    }

    @Override
    public Observable<Boolean> showTutorial()
    {
        return super.loadAggregatedBugsPoints().map(new Func1<AggregatedBugsPoints, Boolean>()
        {
            @Override
            public Boolean call(AggregatedBugsPoints aggregatedBugsPoints)
            {
                return aggregatedBugsPoints.points == 0;
            }
        });
    }

    private T getSyntaxHighlighted(String fileExtension, String codeText)
    {
        String syntaxHighlighted = syntaxHighlighter.getSyntaxHighlightedText(fileExtension, codeText);

        return htmlToDisplay.convertHtmlToDisplayType(syntaxHighlighted, codeText);
    }
}
