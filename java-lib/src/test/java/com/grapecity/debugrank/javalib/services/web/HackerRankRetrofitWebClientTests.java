package com.grapecity.debugrank.javalib.services.web;

import com.grapecity.debugrank.javalib.entities.HackerRankCompileParams;
import com.grapecity.debugrank.javalib.entities.HackerRankResult;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.util.ObserverAdapter;

import org.junit.Before;
import org.junit.Test;

import rx.Observable;

import static org.junit.Assert.assertEquals;

/**
 * Created by chrisripple on 4/17/16.
 */
public class HackerRankRetrofitWebClientTests extends BaseRetrofitWebClientTestCase
{
    private IWebClient<HackerRankResult, HackerRankCompileParams> webClient;

    int size = 0;

    @Before
    public void init()
    {
        webClient = new HackerRankRetrofitWebClient(super.getRetrofit());
    }

    @Test
    public void executeQuery()
    {
        final String EXPECTED_QUERY = String.format("http://www.google.com/checker/submission.json");

        ProgrammingLanguage language = new ProgrammingLanguage("java_8", "Java 8", ".java", 1);

        final String code = "";
        final String testCases = "";

        Observable<HackerRankResult> observable = webClient.enqueue(new HackerRankCompileParams(code, language, testCases));

        observable.subscribe(new ObserverAdapter<HackerRankResult>()
        {
            @Override
            public void onNext(HackerRankResult hackerRankResult)
            {
                //no tests required as all we would be doing is testing retrofit / gson
            }
        });

        //test to make sure the retrofit url is built correctly with correct values
        assertEquals(EXPECTED_QUERY, fullQuery);
    }
}
