package com.grapecity.debugrank.javalib.services.syntax;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by chrisripple on 4/29/16.
 */
public class PrettifySyntaxHighlighterTests
{
    PrettifySyntaxHighlighter syntaxHighlighter;

    @Before
    public void init()
    {
        syntaxHighlighter = new PrettifySyntaxHighlighter();
    }

    @Test
    public void testHighlightning()
    {
        final String EXPECTED = "<font color=\"#a71d5d\">int</font><font color=\"#333333\"> a</font><font color=\"#333333\">;</font>";

        String codeText = syntaxHighlighter.getSyntaxHighlightedText(".java", "int a;");

        System.out.println(codeText);

        assertEquals(EXPECTED, codeText);
    }

//    @Test
//    public void testTabSpacing()
//    {
//        final String EXPECTED = "";
//
//        String codeText = syntaxHighlighter.getSyntaxHighlightedText(".java", "\tstatic int solveMeFirst(int a, int b)");
//
//        System.out.println(codeText);
//
//        //assertEquals(EXPECTED, codeText);
//    }
}
