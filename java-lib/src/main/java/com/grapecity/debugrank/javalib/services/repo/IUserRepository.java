package com.grapecity.debugrank.javalib.services.repo;

import com.grapecity.debugrank.javalib.entities.AggregatedBugsPoints;
import com.grapecity.debugrank.javalib.entities.CompletedPuzzle;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;

import java.util.Map;

import rx.Observable;

/**
 * Created by chrisr on 3/29/2016.
 */
public interface IUserRepository
{
    /**
     * Load the aggregated bugs and points across all the languages.
     *
     * @return aggregated bugs and points.
     */
    Observable<AggregatedBugsPoints> loadAggregatedBugsPoints();

    /**
     * Load the aggregated bugs and points for a specific language.
     *
     * @param programmingLanguage language to aggregate for.
     */
    Observable<AggregatedBugsPoints> loadAggregatedBugsPoints(ProgrammingLanguage programmingLanguage);

    /**
     * Save a completed puzzle.
     *
     * @param programmingLanguage language of the puzzle.
     * @param puzzle              the puzzle completed.
     */
    Observable<CompletedPuzzle> saveCompletedPuzzle(ProgrammingLanguage programmingLanguage, Puzzle puzzle);

    /**
     * Load all the completed puzzles for a particular language.
     *
     * @param programmingLanguage the language of the puzzles.
     */
    Observable<Map<String, CompletedPuzzle>> loadCompletedPuzzles(ProgrammingLanguage programmingLanguage);
}
