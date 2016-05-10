package com.grapecity.debugrank.javalib.ui.solve.code;


import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.ui.base.IMvpPresenter;
import com.grapecity.debugrank.javalib.ui.solve.ICodeEditing;
import com.grapecity.debugrank.javalib.ui.solve.ISolveChildPresenter;
import com.grapecity.debugrank.javalib.ui.solve.ISolveView;

import java.util.List;

import rx.Observable;

/**
 * Created by chrisr on 2/29/2016.
 */
public interface ISolveCodePresenter<T> extends ISolveChildPresenter<ISolveCodeView>, ICodeEditing
{
    void loadCode();

    T getSyntaxHighlightedText(CodeLine codeLine);
}
