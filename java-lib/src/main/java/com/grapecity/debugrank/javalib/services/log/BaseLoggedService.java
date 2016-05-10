package com.grapecity.debugrank.javalib.services.log;

/**
 * Created by chrisr on 3/29/2016.
 */
public abstract class BaseLoggedService
{
    protected final ILogger logger;

    protected BaseLoggedService(ILogger logger)
    {
        this.logger = logger;
    }
}
