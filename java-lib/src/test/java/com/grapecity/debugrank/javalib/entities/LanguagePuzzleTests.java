package com.grapecity.debugrank.javalib.entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by chrisr on 3/28/2016.
 */
public class LanguagePuzzleTests
{
    @Test
    public void constructor()
    {
        ProgrammingLanguage programmingLanguage = new ProgrammingLanguage("qw", "as", "zx", 5);
        Puzzle puzzle = new Puzzle("sadfas","345345",0,0,0, new String[0], new String[0]);

        LanguagePuzzle languagePuzzle = new LanguagePuzzle(programmingLanguage, puzzle);

        assertEquals(programmingLanguage, languagePuzzle.language);
        assertEquals(puzzle, languagePuzzle.puzzle);
    }
}
