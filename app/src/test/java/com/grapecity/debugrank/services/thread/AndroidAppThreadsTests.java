package com.grapecity.debugrank.services.thread;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import com.grapecity.debugrank.BuildConfig;

import rx.android.schedulers.AndroidSchedulers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by chrisripple on 4/20/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class AndroidAppThreadsTests
{
    AndroidAppThreads appThreads;

    @Before
    public void init()
    {
        appThreads = new AndroidAppThreads();
    }

    @Test
    public void testMainThread()
    {
        assertEquals(AndroidSchedulers.mainThread(), appThreads.mainThread());
    }

    @Test
    public void testNewThread()
    {
        //make sure new thread does not return the main thread
        assertNotEquals(AndroidSchedulers.mainThread(), appThreads.newThread());
    }
}
