package com.grapecity.debugrank.javalib.entities;

import java.util.Date;

/**
 * Created by chrisr on 2/12/2016.
 */
public interface CompletedPuzzle
{
    int getBugs();

    String getProgrammingLanguageKey();

    String getPuzzleKey();

    Date getDate();

    int getPoints();

    void setProgrammingLanguageKey(String programmingLanguageKey);

    void setPuzzleKey(String puzzleKey);

    void setDate(Date date);

    void setBugs(int bugs);

    void setPoints(int points);
}
