package com.grapecity.debugrank.services.html;

import android.text.Spanned;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import com.grapecity.debugrank.BuildConfig;

import rx.android.schedulers.AndroidSchedulers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by chrisripple on 4/29/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class AndroidHtmlToDisplayTests
{
    AndroidHtmlToDisplay htmlToDisplay;

    @Before
    public void init()
    {
        htmlToDisplay = new AndroidHtmlToDisplay();
    }

    @Test
    public void testHtmlToDisplay()
    {
        final String EXPECTED = "int a;";

        final String html = "<font color=\"#a71d5d\">int</font><font color=\"#333333\"> a</font><font color=\"#333333\">;</font>";

        Spanned spanned = htmlToDisplay.convertHtmlToDisplayType(html, EXPECTED);

        assertEquals(EXPECTED, spanned.toString());
    }
}