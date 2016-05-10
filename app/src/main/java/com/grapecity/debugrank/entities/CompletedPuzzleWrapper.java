package com.grapecity.debugrank.entities;

import com.grapecity.debugrank.javalib.entities.CompletedPuzzle;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by chrisr on 3/28/2016.
 */
public class CompletedPuzzleWrapper extends RealmObject implements CompletedPuzzle
{
    private String programmingLanguageKey;
    private String puzzleKey;
    private Date date;
    private int bugs;
    private int points;

    public CompletedPuzzleWrapper()
    {

    }

    @Override
    public int getBugs()
    {
        return bugs;
    }

    @Override
    public String getProgrammingLanguageKey()
    {
        return programmingLanguageKey;
    }

    @Override
    public String getPuzzleKey()
    {
        return puzzleKey;
    }

    @Override
    public Date getDate()
    {
        return date;
    }

    @Override
    public int getPoints()
    {
        return points;
    }

    @Override
    public void setProgrammingLanguageKey(String programmingLanguageKey)
    {
        this.programmingLanguageKey = programmingLanguageKey;
    }

    @Override
    public void setPuzzleKey(String puzzleKey)
    {
        this.puzzleKey = puzzleKey;
    }

    @Override
    public void setDate(Date date)
    {
        this.date = date;
    }

    @Override
    public void setBugs(int bugs)
    {
        this.bugs = bugs;
    }

    @Override
    public void setPoints(int points)
    {
        this.points = points;
    }
}
