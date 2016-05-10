package com.grapecity.debugrank.javalib.entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by chrisr on 3/28/2016.
 */
public class HackerRankCompileParamsTests
{
    @Test
    public void constructor()
    {
        ProgrammingLanguage programmingLanguage = new ProgrammingLanguage("qw", "as", "zx", 5);

        final String EXPECTED_CODE = "agdegg";
        final String EXPECTED_TEST = "e56irwgdgg";

        HackerRankCompileParams hackerRankCompileParams = new HackerRankCompileParams(EXPECTED_CODE, programmingLanguage, EXPECTED_TEST);

        assertEquals(programmingLanguage, hackerRankCompileParams.language);
        assertEquals(EXPECTED_CODE, hackerRankCompileParams.code);
        assertEquals(EXPECTED_TEST, hackerRankCompileParams.testCases);
    }
}
