package com.grapecity.debugrank.javalib.entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by chrisr on 3/28/2016.
 */
public class PuzzleTests
{
    @Test
    public void constructor()
    {
        final String EXPECTED_KEY = "sdfgs5";
        final String EXPECTED_NAME = "456347";
        final int EXPECTED_POINTS = 12455;
        final int EXPECTED_TIME = 53256;
        final int EXPECTED_BUG = 553;
        final String[] EXPECTED_TESTCASES = new String[]{"dfgsdfh","5346"};
        final String[] EXPECTED_ANSWERS = new String[]{"df4636","8765", "w46uiwtgsdg"};

        Puzzle puzzle = new Puzzle(EXPECTED_KEY, EXPECTED_NAME, EXPECTED_POINTS, EXPECTED_TIME, EXPECTED_BUG, EXPECTED_TESTCASES, EXPECTED_ANSWERS);

        assertEquals(EXPECTED_KEY, puzzle.key);
        assertEquals(EXPECTED_NAME, puzzle.name);
        assertEquals(EXPECTED_POINTS, puzzle.points);
        assertEquals(EXPECTED_TIME, puzzle.time);
        assertEquals(EXPECTED_BUG, puzzle.bugs);
        assertEquals(EXPECTED_TESTCASES, puzzle.testcases);
        assertEquals(EXPECTED_ANSWERS, puzzle.answers);
    }
}
