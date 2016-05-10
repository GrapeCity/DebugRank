package com.grapecity.debugrank.javalib.entities;

import java.io.Serializable;

/**
 * Created by chrisr on 1/26/2016.
 */
public class Puzzle implements Serializable
{
    public final String key;
    public final String name;
    public final int points;
    public final int time;
    public final int bugs;
    public final String[] testcases;
    public final String[] answers;

    public Puzzle(String key, String name, int points, int time, int bugs, String[] testcases, String[] answers)
    {
        this.key = key;
        this.name = name;
        this.points = points;
        this.time = time;
        this.bugs = bugs;
        this.testcases = testcases;
        this.answers = answers;
    }
}
