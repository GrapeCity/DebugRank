package com.grapecity.debugrank.javalib.entities;

import com.grapecity.debugrank.javalib.ui.languages.ILanguagesInteractor;
import com.grapecity.debugrank.javalib.ui.languages.ILanguagesPresenter;
import com.grapecity.debugrank.javalib.ui.languages.ILanguagesView;
import com.grapecity.debugrank.javalib.ui.languages.LanguagesPresenterImpl;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by chrisr on 3/28/2016.
 */
public class AggregatedBugPointsTests
{
    @Test
    public void constructor()
    {
        AggregatedBugsPoints aggregatedBugsPoints = new AggregatedBugsPoints(145, 643);

        assertEquals(145, aggregatedBugsPoints.bugs);
        assertEquals(643, aggregatedBugsPoints.points);
    }
}
