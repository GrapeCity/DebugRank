package com.grapecity.debugrank.javalib.ui.solve.result;


import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.ui.base.IMvpPresenter;
import com.grapecity.debugrank.javalib.ui.solve.ISolveChildPresenter;
import com.grapecity.debugrank.javalib.ui.solve.ISolveView;
import com.grapecity.debugrank.javalib.ui.solve.code.ISolveCodeView;

import java.util.List;

/**
 * Created by chrisr on 2/29/2016.
 */
public interface ISolveResultPresenter  extends ISolveChildPresenter<ISolveResultView>
{
    void compileCode(List<CodeLine> codeLines);
}
