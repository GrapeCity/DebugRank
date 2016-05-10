package com.grapecity.debugrank.runner;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import com.grapecity.debugrank.TestMyApp;

/**
 * Created by chrisripple on 4/26/16.
 */
public class MyRunner extends AndroidJUnitRunner
{
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        return super.newApplication(cl, TestMyApp.class.getName(), context);
    }
}