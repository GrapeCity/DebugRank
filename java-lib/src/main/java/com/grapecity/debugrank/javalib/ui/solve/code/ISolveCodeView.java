package com.grapecity.debugrank.javalib.ui.solve.code;

import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.ui.solve.ISolveChildView;

import java.util.List;


/**
 * Created by chrisr on 2/29/2016.
 */
public interface ISolveCodeView<T> extends ISolveChildView
{
    void loadCode();

    void codeLoaded(List<CodeLine> result);

    List<CodeLine> getCodeToCompile();

    void syntaxHighlightedTextRefreshed(T displayValue);
}
