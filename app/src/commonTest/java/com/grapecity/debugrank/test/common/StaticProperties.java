package com.grapecity.debugrank.test.common;


public class StaticProperties
{
    //allows us to control all internet services to make them in a "loading" state forever
    private boolean internetUploadsForever = false;

    //allows us to control whether the code should successfully compile or not
    private boolean codeCompilesSuccessfully = true;

    //when true the mock compiler will pass all test cases
    private boolean passAllTestCases = false;

    //allows us to control if "internet" even works
    private boolean internetUnableToReach = false;

    private StaticProperties()
    {
        reset();
    }

    private static StaticProperties instance;

    public static StaticProperties getInstance()
    {
        if(instance == null)
        {
            instance = new StaticProperties();
        }

        return instance;
    }

    public void reset()
    {
        internetUploadsForever = false;
        codeCompilesSuccessfully = true;
        passAllTestCases = false;
        internetUnableToReach = false;
    }

    public boolean isInternetUploadsForever()
    {
        return internetUploadsForever;
    }

    public void setInternetUploadsForever(boolean internetUploadsForever)
    {
        this.internetUploadsForever = internetUploadsForever;
    }

    public boolean isCodeCompilesSuccessfully()
    {
        return codeCompilesSuccessfully;
    }

    public void setCodeCompilesSuccessfully(boolean codeCompilesSuccessfully)
    {
        this.codeCompilesSuccessfully = codeCompilesSuccessfully;
    }

    public boolean isPassAllTestCases()
    {
        return passAllTestCases;
    }

    public void setPassAllTestCases(boolean passAllTestCases)
    {
        this.passAllTestCases = passAllTestCases;
    }

    public boolean isInternetUnableToReach()
    {
        return internetUnableToReach;
    }

    public void setInternetUnableToReach(boolean internetUnableToReach)
    {
        this.internetUnableToReach = internetUnableToReach;
    }
}