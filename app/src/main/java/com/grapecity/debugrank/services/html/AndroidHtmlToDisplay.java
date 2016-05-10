package com.grapecity.debugrank.services.html;

import android.text.Html;
import android.text.Spanned;
import android.text.SpannedString;

import com.grapecity.debugrank.javalib.services.html.IHtmlToDisplay;

/**
 * Created by chrisripple on 4/29/16.
 */
public class AndroidHtmlToDisplay implements IHtmlToDisplay<Spanned>
{
    @Override
    public Spanned convertHtmlToDisplayType(String htmlString, String normalString)
    {
        if(htmlString == null)
        {
            return new SpannedString(normalString);
        }

        return Html.fromHtml(htmlString);
    }
}