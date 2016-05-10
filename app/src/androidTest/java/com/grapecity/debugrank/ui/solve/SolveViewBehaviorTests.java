package com.grapecity.debugrank.ui.solve;

import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;

import org.junit.Rule;
import org.junit.Test;

import com.grapecity.debugrank.R;
import com.grapecity.debugrank.constants.IntentExtras;
import com.grapecity.debugrank.test.common.MemoryDataRepository;
import com.grapecity.debugrank.ui.base.RepositoryBehaviorTest;
import com.grapecity.debugrank.util.Util;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.grapecity.debugrank.util.Util.withDrawable;
import static com.grapecity.debugrank.util.Util.withRecyclerView;
import static junit.framework.Assert.fail;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class SolveViewBehaviorTests extends RepositoryBehaviorTest
{
    private ProgrammingLanguage language;
    private Puzzle puzzle;

    @Rule
    public ActivityTestRule<SolveActivity> mActivityRule = new IntentsTestRule<>(
            SolveActivity.class, true, false);

    private void launchActivityWithIntent()
    {
        MemoryDataRepository repo = new MemoryDataRepository();

        //hack, can't use repo that is injected because we need to access it in order to create intent
        language = repo.getTestProgrammingLanguages().get(0);
        puzzle = repo.getTestPuzzles().get(0);

        Intent intent = new Intent();
        intent.putExtra(IntentExtras.EXTRA_LANGUAGE_KEY, language);
        intent.putExtra(IntentExtras.EXTRA_PUZZLE_KEY, puzzle);

        mActivityRule.launchActivity(intent);

        init(mActivityRule);
    }

    @Test
    public void solveLoadingForever()
    {
        staticProps().setInternetUploadsForever(true);

        launchActivityWithIntent();

        onView(withId(R.id.loadingProgressBar)).check(matches(isDisplayed()));
        onView(withId(R.id.noDataLayout)).check(matches(not(isDisplayed())));

        reset();
    }

    @Test
    public void solveNoInternetConnection()
    {
        staticProps().setInternetUnableToReach(true);

        launchActivityWithIntent();

        onView(withId(R.id.loadingProgressBar)).check(matches(not(isDisplayed())));
        onView(withId(R.id.noDataLayout)).check(matches(isDisplayed()));

        reset();
    }

    @Test
    public void checkLoadedData()
    {
        launchActivityWithIntent();

        onView(withId(R.id.loadingProgressBar)).check(matches(not(isDisplayed())));
        onView(withId(R.id.noDataLayout)).check(matches(not(isDisplayed())));

        //make sure code is loaded
        onView(withId(R.id.flexgrid)).check(Util.flexGridHasCode(dataRepo.getTestCodeLines()));

        //make sure FAB is visible
        onView(withId(R.id.fab)).check(matches(isDisplayed()));

        //make sure FAB is enabled
        onView(withId(R.id.fab)).check(matches(isEnabled()));
    }

    @Test
    public void uploadInProgress()
    {
        launchActivityWithIntent();

        //when set to true will keep app in state of "uploading"
        staticProps().setInternetUploadsForever(true);

        //click the fab to start the "upload"
        onView(withId(R.id.fab)).perform(click());

        //result tab text changes "uploading..."
        onView(withText("uploading...")).check(matches(isDisplayed()));

        //fab image changes -> in progress
        onView(withId(R.id.fab)).check(withDrawable(R.drawable.ic_sync_white_24dp));

        //fab is disabled
        onView(withId(R.id.fab)).check(matches(not(isEnabled())));

        reset();
    }

    @Test
    public void uploadNoInternet()
    {
        launchActivityWithIntent();

        //disable internet after data loaded
        staticProps().setInternetUnableToReach(true);

        //click the fab to start the "upload"
        onView(withId(R.id.fab)).perform(click());

        //result tab text changes "No internet"
        onView(withText("No internet")).check(matches(isDisplayed()));

        onView(withText(R.string.internet_error)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

        reset();
    }

    @Test
    public void checkTestCasesWhenCodeCompiles()
    {
        launchActivityWithIntent();

        //click the fab to start the "upload"
        onView(withId(R.id.fab)).perform(click());

        //click tab to see test case results RecyclerView
        onView(withText("1/2 test cases")).perform(click());

        //validate passed
        testCaseAt(0, "Testcase 1", R.drawable.ic_done_green_24dp, "testcase", "answer passed", "answer passed", "hello msg");

        //validate failed
        testCaseAt(1, "Testcase 2", R.drawable.ic_clear_red_24dp, "testcase", "answer failed", "answer passed", "hello msg");
    }

    @Test
    public void checkTestCasesWhenCodeDoesNotCompile()
    {
        launchActivityWithIntent();

        staticProps().setCodeCompilesSuccessfully(false);

        //click the fab to start the "upload"
        onView(withId(R.id.fab)).perform(click());

        //click tab to see test case results RecyclerView
        onView(allOf(withText("Compile Error(s)"), isCompletelyDisplayed()))
                .perform(click());

        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.scrollToPosition(0));

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(0, R.id.testcase))
                .check(matches(withText("Compile Error(s)")));

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(0, R.id.message))
                .check(matches(withText("compile error")));

        reset();
    }

    private void testCaseAt(int position, String testcase, @DrawableRes int resourceId, String input, String output, String expectedOutput, String message)
    {
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.scrollToPosition(position));

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(position, R.id.testcase))
                .check(matches(withText(testcase)));

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(position, R.id.passed))
                .check(withDrawable(resourceId));

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(position, R.id.input))
                .check(matches(withText(input)));

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(position, R.id.output))
                .check(matches(withText(output)));

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(position, R.id.expectedOutput))
                .check(matches(withText(expectedOutput)));

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(position, R.id.message))
                .check(matches(withText(message)));
    }

    @Test
    public void postUploadFailure()
    {
        launchActivityWithIntent();

        //click the fab to start the "upload"
        onView(withId(R.id.fab)).perform(click());

        //result tab text
        onView(withText("1/2 test cases")).check(matches(isDisplayed()));

        //fab image
        onView(withId(R.id.fab)).check(withDrawable(R.drawable.ic_file_upload_white_24dp));

        // fab is enabled
        onView(withId(R.id.fab)).check(matches(isEnabled()));

        //finished dialog not visible
        onView(withText("ok")).check(doesNotExist());
    }

    @Test
    public void postUploadCompileError()
    {
        launchActivityWithIntent();

        staticProps().setCodeCompilesSuccessfully(false);

        //click the fab to start the "upload"
        onView(withId(R.id.fab)).perform(click());

        //result tab text
        onView(allOf(withText("Compile Error(s)"), isCompletelyDisplayed()));

        //fab image
        onView(withId(R.id.fab)).check(withDrawable(R.drawable.ic_file_upload_white_24dp));

        // fab is enabled
        onView(withId(R.id.fab)).check(matches(isEnabled()));

        //finished dialog not visible
        onView(withText("ok")).check(doesNotExist());

        reset();
    }

    @Test
    public void postUploadSuccess()
    {
        launchActivityWithIntent();

        staticProps().setPassAllTestCases(true);

        //click the fab to start the "upload"
        onView(withId(R.id.fab)).perform(click());

        //finished dialog exists
        onView(withText("ok")).check(matches(isDisplayed()));
        onView(withText("1 points")).check(matches(isDisplayed()));

        reset();
    }

    @Test
    public void timeRunOut()
    {
        //TODO: update app capability to allow less than 60 second puzzles to make a test like this feasible

    }
}