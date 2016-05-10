package com.grapecity.debugrank.javalib.entities;

/**
 * Created by chrisr on 2/12/2016.
 */
public class AggregatedBugsPoints
{
    public final int bugs;
    public final int points;

    public AggregatedBugsPoints(int bugs, int points)
    {
        this.bugs = bugs;
        this.points = points;
    }
}
