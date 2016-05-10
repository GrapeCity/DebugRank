package com.grapecity.debugrank.javalib.entities;

import java.io.Serializable;

/**
 * Created by chrisr on 1/26/2016.
 */
public class CodeLine implements Serializable
{
    public int lineNumber;

    private String codeText;

    public CodeLine(int lineNumber, String codeText)
    {
        this.lineNumber = lineNumber;
        this.codeText = codeText;
    }

    public String getCodeText()
    {
        return codeText;
    }

    public void setCodeText(String codeText)
    {
        this.codeText = codeText;
    }
}
