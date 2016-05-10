package com.grapecity.debugrank.javalib.entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by chrisr on 3/28/2016.
 */
public class CodeLineTests
{
    @Test
    public void constructor()
    {
        final String EXPECTED_TEXT = "aergiubargubrg";

        CodeLine codeLine = new CodeLine(45, EXPECTED_TEXT);

        assertEquals(45, codeLine.lineNumber);
        assertEquals(EXPECTED_TEXT, codeLine.getCodeText());

        final String NEW_EXPECTED_TEXT = "wef8whef87whf78wg";

        codeLine.setCodeText(NEW_EXPECTED_TEXT);

        assertEquals(NEW_EXPECTED_TEXT, codeLine.getCodeText());
    }
}
