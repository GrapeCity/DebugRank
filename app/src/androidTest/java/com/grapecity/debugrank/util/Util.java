package com.grapecity.debugrank.util;

import android.support.annotation.DrawableRes;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.util.HumanReadables;
import android.support.test.espresso.util.TreeIterables;
import android.view.View;
import android.widget.ImageView;

import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.xuni.flexgrid.FlexGrid;

import java.util.List;

import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

/**
 * Created by chrisripple on 4/26/16.
 */
public class Util
{
    public static ViewAssertion flexGridReadOnly(final boolean expected)
    {
        return new ViewAssertion()
        {
            @Override
            public void check(View view, NoMatchingViewException noViewException)
            {
                FlexGrid flexGrid = (FlexGrid) view;

                assertEquals(expected, flexGrid.isReadOnly());
            }
        };
    }

    public static ViewAssertion flexGridHasCode(final List<CodeLine> expectedCodeLines)
    {
        return new ViewAssertion()
        {
            @Override
            public void check(View view, NoMatchingViewException noViewException)
            {
                FlexGrid flexGrid = (FlexGrid) view;

                List<CodeLine> actualCodeLines = (List<CodeLine>) flexGrid.getItemsSource();

                for (int i = 0; i < expectedCodeLines.size(); i++)
                {
                    CodeLine expected = expectedCodeLines.get(i);
                    CodeLine actual = actualCodeLines.get(i);

                    assertEquals(expected.getCodeText(), actual.getCodeText());
                }
            }
        };
    }

    public static ViewAssertion mockDrawable(final String expectedTag)
    {
        return new ViewAssertion()
        {
            @Override
            public void check(View view, NoMatchingViewException noViewException)
            {
                String tag = (String) ((MockDrawable) ((ImageView) view).getDrawable()).getTag();

                assertEquals(expectedTag, tag);
            }
        };
    }

    public static ViewAssertion withDrawable(@DrawableRes final int expectedResourceId)
    {
        return new ViewAssertion()
        {
            @Override
            public void check(View view, NoMatchingViewException noViewException)
            {
                @DrawableRes int tag = (int) ((MockDrawable) ((ImageView) view).getDrawable()).getTag();

                assertEquals(expectedResourceId, tag);
            }
        };
    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId)
    {
        return new RecyclerViewMatcher(recyclerViewId);
    }
}
