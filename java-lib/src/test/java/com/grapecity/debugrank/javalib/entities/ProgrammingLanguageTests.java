package com.grapecity.debugrank.javalib.entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by chrisr on 3/28/2016.
 */
public class ProgrammingLanguageTests
{
    @Test
    public void constructor()
    {
        final String EXPECTED_KEY = "sdfgs5";
        final String EXPECTED_NAME = "456347";
        final String EXPECTED_FILE = "sdfgstj";
        final int EXPECTED_CODE = 5;

        ProgrammingLanguage programmingLanguage = new ProgrammingLanguage(EXPECTED_KEY, EXPECTED_NAME, EXPECTED_FILE, EXPECTED_CODE);

        assertEquals(EXPECTED_KEY, programmingLanguage.key);
        assertEquals(EXPECTED_NAME, programmingLanguage.name);
        assertEquals(EXPECTED_FILE, programmingLanguage.file);
        assertEquals(EXPECTED_CODE, programmingLanguage.code);
    }
}
