package com.grapecity.debugrank.javalib.ui.base;

import com.grapecity.debugrank.javalib.entities.AggregatedBugsPoints;

/**
 * Created by chrisr on 3/31/2016.
 */
public interface IAggregatedBugPointsListener
{
    void aggregatedBugPointsLoaded(AggregatedBugsPoints aggregatedBugsPoints);
}
