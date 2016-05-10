package com.grapecity.debugrank.javalib.util;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StringUtilTests
{
    @Test
    public void testRemoveLeading()
    {
        String text = StringUtil.removeLeadingTrailingCharacter("\t\tint i = 0;", "\t");

        assertEquals("int i = 0;", text);
    }

    @Test
    public void testRemoveTrailing()
    {
        String text = StringUtil.removeLeadingTrailingCharacter("int i = 0;\n", "\n");

        assertEquals("int i = 0;", text);
    }

    @Test
    public void testKeepMiddle()
    {
        String text = StringUtil.removeLeadingTrailingCharacter("String newline = '\n';", "\n");

        assertEquals("String newline = '\n';", text);
    }

    @Test
    public void getCountofLeadingCharacter()
    {
        int count = StringUtil.getCountofLeadingCharacter("\t\tint i = 0;\t\t", "\t");

        assertEquals(2, count);
    }
}