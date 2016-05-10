package com.grapecity.debugrank.ui.base;

import android.support.test.rule.ActivityTestRule;

import com.grapecity.debugrank.test.common.MemoryDataRepository;
import com.grapecity.debugrank.test.common.MemoryUserRepository;

public abstract class RepositoryBehaviorTest extends BaseBehaviorTest
{
    protected MemoryUserRepository userRepo;
    protected MemoryDataRepository dataRepo;

    @Override
    protected void init(ActivityTestRule activityTestRule)
    {
        super.init(activityTestRule);

        userRepo = (MemoryUserRepository) app.getUserRepository();
        dataRepo = (MemoryDataRepository) app.getDataRepository();
    }
}
