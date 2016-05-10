package com.grapecity.debugrank.test.common;

import com.grapecity.debugrank.javalib.entities.AggregatedBugsPoints;
import com.grapecity.debugrank.javalib.entities.CompletedPuzzle;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.services.repo.IUserRepository;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.grapecity.debugrank.entities.CompletedPuzzleWrapper;
import rx.Observable;

/**
 * Created by chrisripple on 4/24/16.
 */
public class MemoryUserRepository implements IUserRepository
{
    Map<String, Map<String, CompletedPuzzle>> completedPuzzleMap = new HashMap<>();

    public MemoryUserRepository()
    {
        completedPuzzleMap.put("java_8", new HashMap<String, CompletedPuzzle>());

        ProgrammingLanguage language = new ProgrammingLanguage("java_8", "Java 8", ".java", 1);
        Puzzle puzzle = new Puzzle("basic_operator", "Basic Operator", 1, 2, 3, new String[]{"testcase"}, new String[]{"answer"});
        saveCompletedPuzzle(language, puzzle);
    }

    public AggregatedBugsPoints getAggregatedBugsPoints()
    {
        List<CompletedPuzzle> completedPuzzles = new ArrayList<>();

        for (Map.Entry<String, CompletedPuzzle> entry : completedPuzzleMap.get("java_8").entrySet())
        {
            completedPuzzles.add(entry.getValue());
        }

        return aggregate(completedPuzzles);
    }

    @Override
    public Observable<AggregatedBugsPoints> loadAggregatedBugsPoints()
    {
        return Observable.just(getAggregatedBugsPoints());
    }

    @Override
    public Observable<AggregatedBugsPoints> loadAggregatedBugsPoints(ProgrammingLanguage programmingLanguage)
    {
        return loadAggregatedBugsPoints();
    }

    private AggregatedBugsPoints aggregate(List<CompletedPuzzle> results)
    {
        int bugs = 0;
        int points = 0;

        for (CompletedPuzzle completedPuzzle : results)
        {
            bugs += completedPuzzle.getBugs();
            points += completedPuzzle.getPoints();
        }

        return new AggregatedBugsPoints(bugs, points);
    }

    @Override
    public Observable<CompletedPuzzle> saveCompletedPuzzle(ProgrammingLanguage programmingLanguage, Puzzle puzzle)
    {
        if (!completedPuzzleMap.containsKey(programmingLanguage.key))
        {
            completedPuzzleMap.put(programmingLanguage.key, new HashMap<String, CompletedPuzzle>());
        }

        CompletedPuzzle completedPuzzle = new CompletedPuzzleWrapper();

        completedPuzzle.setProgrammingLanguageKey(programmingLanguage.key);
        completedPuzzle.setPuzzleKey(puzzle.key);
        completedPuzzle.setPoints(puzzle.points);
        completedPuzzle.setBugs(puzzle.bugs);
        completedPuzzle.setDate(GregorianCalendar.getInstance().getTime());

        completedPuzzleMap.get(programmingLanguage.key).put(puzzle.key, completedPuzzle);

        return Observable.just(completedPuzzle);
    }

    @Override
    public Observable<Map<String, CompletedPuzzle>> loadCompletedPuzzles(ProgrammingLanguage programmingLanguage)
    {
        return Observable.just(completedPuzzleMap.get(programmingLanguage.key));
    }
}
