package com.grapecity.debugrank.javalib.entities;

/**
 * Created by chrisr on 3/29/2016.
 */
public class LanguagePuzzle
{
    public final ProgrammingLanguage language;
    public final Puzzle puzzle;

    public LanguagePuzzle(ProgrammingLanguage language, Puzzle puzzle)
    {
        this.language = language;
        this.puzzle = puzzle;
    }
}
