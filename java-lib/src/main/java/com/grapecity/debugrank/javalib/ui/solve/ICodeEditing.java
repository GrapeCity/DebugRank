package com.grapecity.debugrank.javalib.ui.solve;

import com.grapecity.debugrank.javalib.entities.CodeLine;

public interface ICodeEditing
{
    void beginEditing(CodeLine codeLine);

    void finishEditing(CodeLine codeLine);
}
