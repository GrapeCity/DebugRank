package com.grapecity.debugrank.ui.languages;

import android.content.ComponentName;
import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.grapecity.debugrank.javalib.entities.AggregatedBugsPoints;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import com.grapecity.debugrank.R;
import com.grapecity.debugrank.ui.base.RepositoryBehaviorTest;
import com.grapecity.debugrank.ui.puzzles.PuzzlesActivity;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Checks.checkNotNull;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.grapecity.debugrank.util.Util.mockDrawable;
import static com.grapecity.debugrank.util.Util.withRecyclerView;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by chrisripple on 4/24/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LanguagesViewBehaviorTests extends RepositoryBehaviorTest
{
    @Rule
    public ActivityTestRule<LanguagesActivity> mActivityRule = new IntentsTestRule<>(
            LanguagesActivity.class, true, false);


    private void launchActivityWithIntent()
    {
        Intent intent = new Intent();

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
    public void listProgrammingLoadingForever()
    {
        staticProps().setInternetUploadsForever(true);

        launchActivityWithIntent();

        onView(withId(R.id.loadingProgressBar)).check(matches(isDisplayed()));
        onView(withId(R.id.noDataLayout)).check(matches(not(isDisplayed())));

        reset();
    }

    @Test
    public void listProgrammingNoInternetConnection()
    {
        staticProps().setInternetUnableToReach(true);

        launchActivityWithIntent();

        onView(withId(R.id.loadingProgressBar)).check(matches(not(isDisplayed())));
        onView(withId(R.id.noDataLayout)).check(matches(isDisplayed()));

        reset();
    }

    @Test
    public void listProgrammingLanguages()
    {
        launchActivityWithIntent();

        onView(withId(R.id.loadingProgressBar)).check(matches(not(isDisplayed())));
        onView(withId(R.id.noDataLayout)).check(matches(not(isDisplayed())));

        //load the languages from the test memory repo that's injected by dagger
        final List<ProgrammingLanguage> programmingLanguageList = dataRepo.getTestProgrammingLanguages();

        int position = 0;

        for (final ProgrammingLanguage language : programmingLanguageList)
        {
            onView(withId(R.id.recyclerView))
                    .perform(RecyclerViewActions.scrollToPosition(position));

            //make sure the language name is set foreach language in the recyclerview
            onView(withRecyclerView(R.id.recyclerView)
                    .atPositionOnView(position, R.id.languageName))
                    .check(matches(withText(language.name)));

            //make sure the image loading service was correctly called
            onView(withRecyclerView(R.id.recyclerView)
                    .atPositionOnView(position, R.id.languageIcon))
                    .check(mockDrawable(language.key));

            position++;
        }
    }

    @Test
    public void viewPuzzlesForLanguage()
    {
        launchActivityWithIntent();

        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        //make sure clicking on an language navigates to puzzle activity
        intended(hasComponent(new ComponentName(getTargetContext(), PuzzlesActivity.class)));
    }
}
