package com.grapecity.debugrank.javalib.constants;

/**
 * Created by chrisr on 1/27/2016.
 */
public class Api
{
    public static final String GITHUB_API = "https://raw.githubusercontent.com";
    public static final String HACKERRANK_API = "http://api.hackerrank.com";

    public static final String HACKERRANK_API_KEY = "hackerrank|912642-602|41df1bd9d20c89dbc9ed667af33c9174babeb265";

    public static final String GITHUB_API_OWNER = "grapecity";
    public static final String GITHUB_API_REPO = "DebugRank";
    public static final String GITHUB_API_BRANCH = "master";
    public static final String GITHUB_API_LANGUAGES_PATH = "data/languages.json";

    public static final String GITHUB_API_IMAGE_URL = GITHUB_API + "/" + GITHUB_API_OWNER + "/" + GITHUB_API_REPO + "/" + GITHUB_API_BRANCH + "/data/images/%s.png";

}
