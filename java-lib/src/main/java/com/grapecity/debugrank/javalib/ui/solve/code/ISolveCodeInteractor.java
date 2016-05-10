package com.grapecity.debugrank.javalib.ui.solve.code;

import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.ui.base.IMvpInteractor;

import java.util.List;

import rx.Observable;

/**
 * Created by chrisr on 2/29/2016.
 */
public interface ISolveCodeInteractor<T> extends IMvpInteractor
{
    Observable<List<CodeLine>> loadCodeFile(ProgrammingLanguage language, Puzzle puzzle);

    T getSyntaxHighlightedText(ProgrammingLanguage language, CodeLine codeLine);

    Observable<T> refreshSyntaxHighlightedTextCache(ProgrammingLanguage language, CodeLine codeLine);
}
