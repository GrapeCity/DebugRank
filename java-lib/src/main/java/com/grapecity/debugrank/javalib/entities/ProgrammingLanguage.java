package com.grapecity.debugrank.javalib.entities;

import java.io.Serializable;

/**
 * Created by Ripple on 1/25/2016.
 */
public class ProgrammingLanguage implements Serializable
{
    /*
    * i.e. "java_8"
     */
    public final String key;

    /*
    * i.e. "Java 8"
     */
    public final String name;

    /*
    * i.e. ".java"
     */
    public final String file;

    /*
     * http://api.hackerrank.com/checker/languages.json
     */
    public final int code;

    public ProgrammingLanguage(String key, String name, String file, int code)
    {
        this.key = key;
        this.name = name;
        this.file = file;
        this.code = code;
    }
}
