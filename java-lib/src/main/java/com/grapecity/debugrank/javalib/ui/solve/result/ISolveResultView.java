package com.grapecity.debugrank.javalib.ui.solve.result;

import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.ui.solve.ISolveChildView;

import java.util.List;


/**
 * Created by chrisr on 2/29/2016.
 */
public interface ISolveResultView extends ISolveChildView
{
    void compileCode(List<CodeLine> codeLines);
}
