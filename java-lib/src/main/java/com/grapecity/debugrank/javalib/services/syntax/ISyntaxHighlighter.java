package com.grapecity.debugrank.javalib.services.syntax;

/**
 * Created by chrisripple on 4/29/16.
 */
public interface ISyntaxHighlighter
{
    String getSyntaxHighlightedText(String fileExtension, String codeText);
}
