package com.grapecity.debugrank.javalib.entities;

/**
 * Created by chrisr on 3/29/2016.
 */
public class HackerRankCompileParams
{
    public final String code;
    public final ProgrammingLanguage language;
    public final String testCases;

    public HackerRankCompileParams(String code, ProgrammingLanguage language, String testCases)
    {
        this.code = code;
        this.language = language;
        this.testCases = testCases;
    }
}
