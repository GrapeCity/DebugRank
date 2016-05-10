package com.grapecity.debugrank.javalib.services.deserializer;

import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.HackerRankResult;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.entities.Result;
import com.grapecity.debugrank.javalib.entities.TestCaseResult;
import com.grapecity.debugrank.javalib.services.compile.HackerRankCompilerApi;
import com.grapecity.debugrank.javalib.services.compile.ICompilerApi;
import com.grapecity.debugrank.javalib.services.log.ILogger;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by chrisripple on 4/17/16.
 */
public class DeserializerImplTests
{
    IDeserializer deserializer;

    @Before
    public void init()
    {
        ILogger logger = mock(ILogger.class);
        deserializer = new DeserializerImpl(logger);
    }

    @Test
    public void deserializeCodeLines()
    {
        List<CodeLine> codeLines = deserializer.deserializeCodeLines("code line 1\ncode line 2\ncode line 3");

        assertEquals(3, codeLines.size());

        assertEquals(1, codeLines.get(0).lineNumber);
        assertEquals("code line 1", codeLines.get(0).getCodeText());

        assertEquals(2, codeLines.get(1).lineNumber);
        assertEquals("code line 2", codeLines.get(1).getCodeText());

        assertEquals(3, codeLines.get(2).lineNumber);
        assertEquals("code line 3", codeLines.get(2).getCodeText());
    }
}
