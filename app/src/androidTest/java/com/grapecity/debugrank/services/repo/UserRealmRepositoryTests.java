package com.grapecity.debugrank.services.repo;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.grapecity.debugrank.javalib.entities.AggregatedBugsPoints;
import com.grapecity.debugrank.javalib.entities.CompletedPuzzle;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.services.log.ILogger;
import com.grapecity.debugrank.javalib.util.ObserverAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import com.grapecity.debugrank.BuildConfig;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.internal.Context;

import static org.mockito.Mockito.mock;

/**
 * Created by chrisripple on 4/22/16.
 */
public class UserRealmRepositoryTests extends AndroidTestCase
{
    Realm realm;
    UserRealmRepository repository;

    public UserRealmRepositoryTests()
    {
        super();
    }

    @Override
    public void setUp() throws Exception
    {
        super.setUp();

        RealmConfiguration config = new RealmConfiguration.Builder(getContext()).
                schemaVersion(1).
                name("test.realm").
                inMemory().
                build();

        Realm.setDefaultConfiguration(config);

        realm = Realm.getInstance(config);

        ILogger logger = mock(ILogger.class);

        repository = new UserRealmRepository(realm, logger);

        //every test method needs data
        ProgrammingLanguage language = new ProgrammingLanguage("java_8", "Java 8", ".java", 1);
        Puzzle puzzle = new Puzzle("basic_operator", "Basic Operator", 1, 2, 3, new String[]{"testcase"}, new String[]{"answer"});
        repository.saveCompletedPuzzle(language, puzzle);

        language = new ProgrammingLanguage("javascript", "JavaScript", ".js", 2);
        puzzle = new Puzzle("basic_operator", "Basic Operator", 4, 5, 6, new String[]{"testcase"}, new String[]{"answer"});
        repository.saveCompletedPuzzle(language, puzzle);
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();

        realm.close();
    }

    @SmallTest
    public void testLoadAggregatedBugsPoints()
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

        assertEquals(5, aggregatedBugsPoints.points);
        assertEquals(9, aggregatedBugsPoints.bugs);
    }

    @SmallTest
    public void testLoadAggregatedBugsPointsForLanguage()
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

    @SmallTest
    public void testLoadCompletedPuzzles()
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

        Calendar currentTime = GregorianCalendar.getInstance();

        Calendar completedTime = GregorianCalendar.getInstance();
        completedTime.setTimeInMillis(completedPuzzle.getDate().getTime());

        assertEquals(currentTime.get(Calendar.YEAR), completedTime.get(Calendar.YEAR));
        assertEquals(currentTime.get(Calendar.MONTH), completedTime.get(Calendar.MONTH));
        assertEquals(currentTime.get(Calendar.DAY_OF_MONTH), completedTime.get(Calendar.DAY_OF_MONTH));
    }
}
