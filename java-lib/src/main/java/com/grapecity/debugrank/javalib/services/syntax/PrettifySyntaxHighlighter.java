package com.grapecity.debugrank.javalib.services.syntax;

import com.grapecity.debugrank.javalib.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import prettify.PrettifyParser;
import syntaxhighlight.ParseResult;
import syntaxhighlight.Parser;

/**
 * Created by chrisripple on 4/29/16.
 */
public class PrettifySyntaxHighlighter implements ISyntaxHighlighter
{
    private static final String FONT_PATTERN = "<font color=\"#%s\">%s</font>";

    private static final Map<String, String> COLORS = new HashMap<String, String>()
    {{
        put("typ", "795da3");
        put("kwd", "a71d5d");
        put("lit", "0086b3");
        put("com", "999999");
        put("str", "183691");
        put("pun", "333333");
        put("pln", "333333");
    }};

    private final Parser parser = new PrettifyParser();

    @Override
    public String getSyntaxHighlightedText(String fileExtension, String codeText)
    {
        String codeTextToHighlight = codeText;

        StringBuilder highlighted = new StringBuilder();

        List<ParseResult> results = parser.parse(fileExtension, codeTextToHighlight);

        for (ParseResult result : results)
        {
            String type = result.getStyleKeys().get(0);

            String content = codeTextToHighlight.substring(result.getOffset(), result.getOffset() + result.getLength());

            highlighted.append(String.format(FONT_PATTERN, getColor(type), content));
        }

        return highlighted.toString();
    }

    private String getColor(String type)
    {
        return COLORS.containsKey(type) ? COLORS.get(type) : COLORS.get("pln");
    }
}
