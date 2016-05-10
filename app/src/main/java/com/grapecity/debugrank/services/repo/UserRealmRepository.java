package com.grapecity.debugrank.services.repo;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.grapecity.debugrank.javalib.entities.CompletedPuzzle;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.entities.AggregatedBugsPoints;
import com.grapecity.debugrank.javalib.services.log.BaseLoggedService;
import com.grapecity.debugrank.javalib.services.log.ILogger;
import com.grapecity.debugrank.javalib.services.repo.IUserRepository;

import com.grapecity.debugrank.entities.CompletedPuzzleWrapper;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

/**
 * Wrap all Sugar ORM commands in a repository so we can easily change implementation later.
 * <p/>
 * Created by chrisr on 3/25/2016.
 */
public class UserRealmRepository extends BaseLoggedService implements IUserRepository
{
    private static final String TAG = "UserRealmRepository";

    private Realm realm;

    public UserRealmRepository(Realm realm, ILogger logger)
    {
        super(logger);

        this.realm = realm;
    }

    private RealmResults<CompletedPuzzleWrapper> getCompletedPuzzlesForLanguage(ProgrammingLanguage programmingLanguage)
    {
        return this.realm.where(CompletedPuzzleWrapper.class).equalTo("programmingLanguageKey", programmingLanguage.key).findAll();
    }

    private AggregatedBugsPoints aggregate(RealmResults<CompletedPuzzleWrapper> results)
    {
        int bugs = results.sum("bugs").intValue();
        int points = results.sum("points").intValue();

        return new AggregatedBugsPoints(bugs, points);
    }

    @Override
    public Observable<AggregatedBugsPoints> loadAggregatedBugsPoints()
    {
        try
        {
            return Observable.just(aggregate(realm.where(CompletedPuzzleWrapper.class).findAll()));

        } catch (Exception e)
        {
            logger.error(TAG, e.getMessage());
        }

        return Observable.empty();
    }

    @Override
    public Observable<AggregatedBugsPoints> loadAggregatedBugsPoints(ProgrammingLanguage programmingLanguage)
    {
        try
        {
            return Observable.just(aggregate(getCompletedPuzzlesForLanguage(programmingLanguage)));

        } catch (Exception e)
        {
            logger.error(TAG, e.getMessage());
        }

        return Observable.empty();
    }

    @Override
    public Observable<CompletedPuzzle> saveCompletedPuzzle(ProgrammingLanguage programmingLanguage, Puzzle puzzle)
    {
        try
        {
            this.realm.beginTransaction();

            CompletedPuzzle completedPuzzle = realm.createObject(CompletedPuzzleWrapper.class);

            completedPuzzle.setProgrammingLanguageKey(programmingLanguage.key);
            completedPuzzle.setPuzzleKey(puzzle.key);
            completedPuzzle.setPoints(puzzle.points);
            completedPuzzle.setBugs(puzzle.bugs);
            completedPuzzle.setDate(GregorianCalendar.getInstance().getTime());

            this.realm.commitTransaction();

            return Observable.just(completedPuzzle);

        } catch (Exception e)
        {
            logger.error(TAG, e.getMessage());
        }

        return Observable.empty();
    }

    @Override
    public Observable<Map<String, CompletedPuzzle>> loadCompletedPuzzles(ProgrammingLanguage programmingLanguage)
    {
        try
        {
            Map<String, CompletedPuzzle> completedPuzzleMap = new HashMap<>();

            List<CompletedPuzzleWrapper> completedPuzzles = getCompletedPuzzlesForLanguage(programmingLanguage);

            for (CompletedPuzzle completedPuzzle : completedPuzzles)
            {
                completedPuzzleMap.put(completedPuzzle.getPuzzleKey(), completedPuzzle);
            }

            return Observable.just(completedPuzzleMap);

        } catch (Exception e)
        {
            logger.error(TAG, e.getMessage());
        }

        return Observable.empty();
    }
}
