package com.grapecity.debugrank.ui.base;

import android.support.test.rule.ActivityTestRule;

import com.grapecity.debugrank.TestMyApp;
import com.grapecity.debugrank.test.common.StaticProperties;

public abstract class BaseBehaviorTest
{
    protected TestMyApp app;

    protected void init(ActivityTestRule activityTestRule)
    {
        app = (TestMyApp) activityTestRule.getActivity().getApplication();
    }

    protected StaticProperties staticProps()
    {
        return StaticProperties.getInstance();
    }

    protected void reset()
    {
        staticProps().reset();
    }
}
