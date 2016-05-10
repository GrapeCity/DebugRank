package com.grapecity.debugrank.entities;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/**
 * Created by chrisripple on 4/22/16.
 */
public class CompletedPuzzleWrapperTests
{
    CompletedPuzzleWrapper completedPuzzleWrapper;

    @Before
    public void init()
    {
        completedPuzzleWrapper = new CompletedPuzzleWrapper();
    }

    @Test
    public void testPuzzleKey()
    {
        final String EXPECTED = "KEY";

        completedPuzzleWrapper.setPuzzleKey(EXPECTED);

        assertEquals(EXPECTED, completedPuzzleWrapper.getPuzzleKey());
    }

    @Test
    public void testLanguageKey()
    {
        final String EXPECTED = "KEY";

        completedPuzzleWrapper.setProgrammingLanguageKey(EXPECTED);

        assertEquals(EXPECTED, completedPuzzleWrapper.getProgrammingLanguageKey());
    }

    @Test
    public void testBugs()
    {
        final int EXPECTED = 5;

        completedPuzzleWrapper.setBugs(EXPECTED);

        assertEquals(EXPECTED, completedPuzzleWrapper.getBugs());
    }

    @Test
    public void testDateKey()
    {
        final Date EXPECTED = GregorianCalendar.getInstance().getTime();

        completedPuzzleWrapper.setDate(EXPECTED);

        assertEquals(EXPECTED, completedPuzzleWrapper.getDate());
    }

    @Test
    public void testPointsKey()
    {
        final int EXPECTED = 5;

        completedPuzzleWrapper.setPoints(EXPECTED);

        assertEquals(EXPECTED, completedPuzzleWrapper.getPoints());
    }
}
