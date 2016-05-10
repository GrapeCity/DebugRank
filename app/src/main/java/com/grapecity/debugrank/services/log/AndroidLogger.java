package com.grapecity.debugrank.services.log;

import android.util.Log;

import com.grapecity.debugrank.javalib.services.log.ILogger;

/**
 * Created by chrisr on 3/29/2016.
 */
public class AndroidLogger implements ILogger
{
    @Override
    public void debug(String tag, String text)
    {
        Log.d(tag, text);
    }

    @Override
    public void info(String tag, String text)
    {
        Log.i(tag, text);
    }

    @Override
    public void warn(String tag, String text)
    {
        Log.w(tag, text);
    }

    @Override
    public void error(String tag, String text)
    {
        Log.e(tag, text);
    }
}
