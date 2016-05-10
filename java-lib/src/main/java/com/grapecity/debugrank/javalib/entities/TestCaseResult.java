package com.grapecity.debugrank.javalib.entities;

/**
 * Created by chrisr on 2/11/2016.
 */
public class TestCaseResult
{
    public final int testCase;
    public final boolean passed;
    public final String[] input;
    public final String output;
    public final String expectedOutput;
    public final String message;


    public TestCaseResult(int testCase, String[] input, String output, String expectedOutput, String message)
    {
        this.passed = output != null && expectedOutput != null ? output.equals(expectedOutput) : false;
        this.testCase = testCase;
        this.input = input;
        this.output = output;
        this.expectedOutput = expectedOutput;
        this.message = message;
    }

    public TestCaseResult(String message)
    {
        //compile error constructor

        this.passed = false;
        this.testCase = Integer.MIN_VALUE;
        this.input = null;
        this.output = null;
        this.expectedOutput = null;
        this.message = message;
    }

    public boolean isCompileError()
    {
        return testCase == Integer.MIN_VALUE;
    }
}
