package com.grapecity.debugrank.javalib.services.web;

import com.grapecity.debugrank.javalib.constants.Api;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.util.ObserverAdapter;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import rx.Observable;

import static org.junit.Assert.assertEquals;

/**
 * Created by chrisripple on 4/17/16.
 */
public class GithubLanguagesRetrofitWebClientTests extends BaseRetrofitWebClientTestCase
{
    private IWebClient<List<ProgrammingLanguage>, Void> webClient;

    int size = 0;

    @Before
    public void init()
    {
        webClient = new GithubLanguagesRetrofitWebClient(super.getRetrofit());
    }

    @Test
    public void executeQuery()
    {
        final String EXPECTED_QUERY = String.format("http://www.google.com/%s/%s/%s/%s", Api.GITHUB_API_OWNER, Api.GITHUB_API_REPO, Api.GITHUB_API_BRANCH, Api.GITHUB_API_LANGUAGES_PATH);

        Observable<List<ProgrammingLanguage>> observable = webClient.enqueue(null);

        observable.subscribe(new ObserverAdapter<List<ProgrammingLanguage>>()
        {
            @Override
            public void onNext(List<ProgrammingLanguage> programmingLanguages)
            {
                //no tests required as all we would be doing is testing retrofit / gson
            }
        });

        //test to make sure the retrofit url is built correctly with correct values
        assertEquals(EXPECTED_QUERY, fullQuery);
    }
}
