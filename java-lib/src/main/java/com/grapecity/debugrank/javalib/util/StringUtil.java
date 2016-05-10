package com.grapecity.debugrank.javalib.util;

public class StringUtil
{
    public static String removeLeadingTrailingCharacter(String text, String character)
    {
        String returnText = text;

        returnText = removeLeadingCharacter(returnText, character);
        returnText = removeTrailingCharacter(returnText, character);

        return returnText;
    }

    public static String removeLeadingCharacter(String text, String character)
    {
        return text.replaceAll("^" + character + "+", "");
    }

    public static String removeTrailingCharacter(String text, String character)
    {
        return text.replaceAll(character + "+$", "");
    }

    public static int getCountofLeadingCharacter(String text, String character)
    {
        return text.length() - removeLeadingCharacter(text, character).length();
    }
}