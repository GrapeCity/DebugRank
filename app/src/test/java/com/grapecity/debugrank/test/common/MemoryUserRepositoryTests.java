package com.grapecity.debugrank.test.common;

import com.grapecity.debugrank.javalib.entities.AggregatedBugsPoints;
import com.grapecity.debugrank.javalib.entities.CompletedPuzzle;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.util.ObserverAdapter;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by chrisripple on 4/24/16.
 */
public class MemoryUserRepositoryTests
{
    MemoryUserRepository repository;

    @Before
    public void init()
    {
        repository = new MemoryUserRepository();

        ProgrammingLanguage language = new ProgrammingLanguage("java_8", "Java 8", ".java", 1);
        Puzzle puzzle = new Puzzle("basic_operator", "Basic Operator", 1, 2, 3, new String[]{"testcase"}, new String[]{"answer"});
        repository.saveCompletedPuzzle(language, puzzle);
    }

    @Test
    public void loadAggregatedBugsPoints()
    {
        final List<AggregatedBugsPoints> list = new ArrayList<>();

        repository.loadAggregatedBugsPoints().subscribe(new ObserverAdapter<AggregatedBugsPoints>()
        {
            @Override
            public void onNext(AggregatedBugsPoints aggregatedBugsPoints)
            {
                list.add(aggregatedBugsPoints);
            }
        });

        AggregatedBugsPoints aggregatedBugsPoints = list.get(0);

        assertEquals(1, aggregatedBugsPoints.points);
        assertEquals(3, aggregatedBugsPoints.bugs);
    }

    @Test
    public void loadAggregatedBugsPointsForLanguages()
    {
        final List<AggregatedBugsPoints> list = new ArrayList<>();

        ProgrammingLanguage language = new ProgrammingLanguage("java_8", "Java 8", ".java", 1);

        repository.loadAggregatedBugsPoints(language).subscribe(new ObserverAdapter<AggregatedBugsPoints>()
        {
            @Override
            public void onNext(AggregatedBugsPoints aggregatedBugsPoints)
            {
                list.add(aggregatedBugsPoints);
            }
        });

        AggregatedBugsPoints aggregatedBugsPoints = list.get(0);

        assertEquals(1, aggregatedBugsPoints.points);
        assertEquals(3, aggregatedBugsPoints.bugs);
    }

    @Test
    public void loadCompletedPuzzles()
    {
        final List<Map<String, CompletedPuzzle>> list = new ArrayList<>();

        ProgrammingLanguage language = new ProgrammingLanguage("java_8", "Java 8", ".java", 1);

        repository.loadCompletedPuzzles(language).subscribe(new ObserverAdapter<Map<String, CompletedPuzzle>>()
        {
            @Override
            public void onNext(Map<String, CompletedPuzzle> completedPuzzleMap)
            {
                list.add(completedPuzzleMap);
            }
        });

        Map<String, CompletedPuzzle> completedPuzzleMap = list.get(0);

        CompletedPuzzle completedPuzzle = (CompletedPuzzle) completedPuzzleMap.values().toArray()[0];

        assertEquals("basic_operator", (String) completedPuzzleMap.keySet().toArray()[0]);
        assertEquals(3, completedPuzzle.getBugs());
        assertEquals(1, completedPuzzle.getPoints());
        assertEquals("java_8", completedPuzzle.getProgrammingLanguageKey());
        assertEquals("basic_operator", completedPuzzle.getPuzzleKey());
    }
}
