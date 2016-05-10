package com.grapecity.debugrank.javalib.entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by chrisr on 3/28/2016.
 */
public class TestCaseResultTests
{
    @Test
    public void constructor()
    {

        final int EXPECTED_TESTCASE = 12455;
        final String[] EXPECTED_INPUT = new String[]{"dfgsdfh","5346"};
        final String EXPECTED_OUTPUT = "sdfgs5";
        final String EXPECTED_EXPECTED_OUTPUT = "456347";
        final String EXPECTED_MESSAGE = "456456";

        TestCaseResult testCaseResult = new TestCaseResult(EXPECTED_TESTCASE, EXPECTED_INPUT, EXPECTED_OUTPUT, EXPECTED_EXPECTED_OUTPUT, EXPECTED_MESSAGE);

        assertEquals(EXPECTED_TESTCASE, testCaseResult.testCase);
        assertEquals(EXPECTED_INPUT, testCaseResult.input);
        assertEquals(EXPECTED_OUTPUT, testCaseResult.output);
        assertEquals(EXPECTED_EXPECTED_OUTPUT, testCaseResult.expectedOutput);
        assertEquals(EXPECTED_MESSAGE, testCaseResult.message);
    }
}
