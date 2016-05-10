package com.grapecity.debugrank.javalib.ui.solve;

import com.grapecity.debugrank.javalib.entities.CodeLine;

/**
 * Created by chrisr on 3/31/2016.
 */
public interface ISolveView extends ISolveChildView, ICodeEditing
{
    void solveCodeLoaded();

    void unableToLoadSolveCode();

    void unableToUploadAndCompileCode();
}
