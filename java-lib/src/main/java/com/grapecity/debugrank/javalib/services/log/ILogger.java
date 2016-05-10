package com.grapecity.debugrank.javalib.services.log;

/**
 * Created by chrisr on 3/29/2016.
 */
public interface ILogger
{
    void debug(String tag, String text);
    void info(String tag, String text);
    void warn(String tag, String text);
    void error(String tag, String text);
}
