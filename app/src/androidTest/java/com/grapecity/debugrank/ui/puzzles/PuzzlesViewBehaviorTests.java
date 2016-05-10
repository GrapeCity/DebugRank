package com.grapecity.debugrank.ui.puzzles;

import android.content.ComponentName;
import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.grapecity.debugrank.javalib.entities.AggregatedBugsPoints;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import com.grapecity.debugrank.R;
import com.grapecity.debugrank.constants.IntentExtras;
import com.grapecity.debugrank.test.common.MemoryDataRepository;
import com.grapecity.debugrank.ui.base.RepositoryBehaviorTest;
import com.grapecity.debugrank.ui.solve.SolveActivity;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.grapecity.debugrank.util.Util.mockDrawable;
import static com.grapecity.debugrank.util.Util.withRecyclerView;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by chrisripple on 4/27/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class PuzzlesViewBehaviorTests extends RepositoryBehaviorTest
{
    @Rule
    public ActivityTestRule<PuzzlesActivity> mActivityRule = new IntentsTestRule<>(
            PuzzlesActivity.class, true, false);

    private ProgrammingLanguage language;

    private void launchActivityWithIntent()
    {
        //hack, can't use repo that is injected because we need to access it in order to create intent
        language = new MemoryDataRepository().getTestProgrammingLanguages().get(0);

        Intent intent = new Intent();
        intent.putExtra(IntentExtras.EXTRA_LANGUAGE_KEY, language);
        mActivityRule.launchActivity(intent);

        init(mActivityRule);
    }

    @Test
    public void seeIfToolbarSet()
    {
        launchActivityWithIntent();

        AggregatedBugsPoints aggregatedBugsPoints = userRepo.getAggregatedBugsPoints();

        //check to make sure the text is set correctly to values of MemoryUserRepo
        onView(allOf(withId(R.id.bugs), withParent(withId(R.id.toolbarBugPointsTimer))))
                .check(matches(withText(String.valueOf(aggregatedBugsPoints.bugs))));

        //check to make sure the text is set correctly to values of MemoryUserRepo
        onView(allOf(withId(R.id.points), withParent(withId(R.id.toolbarBugPointsTimer))))
                .check(matches(withText(String.valueOf(aggregatedBugsPoints.points))));

        //make sure timer is hidden
        onView(allOf(withId(R.id.time), withParent(withId(R.id.toolbarBugPointsTimer))))
                .check(doesNotExist());
    }

    @Test
    public void seeIfLanguageSet()
    {
        launchActivityWithIntent();

        //make sure language name is set for header
        onView(withId(R.id.languageName)).check(matches(withText(language.name)));

        //make sure correct image is loaded for header
        onView(withId(R.id.languageIcon)).check(mockDrawable(language.key));
    }

    @Test
    public void listPuzzlesLoadingForever()
    {
        staticProps().setInternetUploadsForever(true);

        launchActivityWithIntent();

        onView(withId(R.id.loadingProgressBar)).check(matches(isDisplayed()));
        onView(withId(R.id.noDataLayout)).check(matches(not(isDisplayed())));

        reset();
    }

    @Test
    public void listPuzzlesNoInternetConnection()
    {
        staticProps().setInternetUnableToReach(true);

        launchActivityWithIntent();

        onView(withId(R.id.loadingProgressBar)).check(matches(not(isDisplayed())));
        onView(withId(R.id.noDataLayout)).check(matches(isDisplayed()));

        reset();
    }

    @Test
    public void listPuzzles()
    {
        launchActivityWithIntent();

        onView(withId(R.id.loadingProgressBar)).check(matches(not(isDisplayed())));
        onView(withId(R.id.noDataLayout)).check(matches(not(isDisplayed())));

        //load the puzzles from the test memory repo that's injected by dagger
        final List<Puzzle> puzzzleList = dataRepo.getTestPuzzles();

        //start at position 1 for puzzles,  as the language header is at position 0
        int position = 1;

        for (final Puzzle puzzle : puzzzleList)
        {
            onView(withId(R.id.recyclerView))
                    .perform(RecyclerViewActions.scrollToPosition(position));

            onView(withRecyclerView(R.id.recyclerView)
                    .atPositionOnView(position, R.id.puzzleName))
                    .check(matches(withText(puzzle.name)));

            onView(withRecyclerView(R.id.recyclerView)
                    .atPositionOnView(position, R.id.points))
                    .check(matches(withText(String.format("%d\npoints", puzzle.points))));

            onView(withRecyclerView(R.id.recyclerView)
                    .atPositionOnView(position, R.id.minutes))
                    .check(matches(withText(String.format("%d:00\nminutes", (int) Math.round(puzzle.time / 60)))));

            onView(withRecyclerView(R.id.recyclerView)
                    .atPositionOnView(position, R.id.bugs))
                    .check(matches(withText(String.format("%d\nbugs", puzzle.bugs))));

            position++;
        }
    }

    @Test
    public void viewSolvePuzzle()
    {
        launchActivityWithIntent();

        //click second puzzle as first puzzle is already completed
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));

        //make sure clicking on an language navigates to puzzle activity
        intended(hasComponent(new ComponentName(getTargetContext(), SolveActivity.class)));
    }
}
