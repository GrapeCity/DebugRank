package com.grapecity.debugrank.javalib.services.web;

import com.grapecity.debugrank.javalib.constants.Api;
import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.LanguagePuzzle;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.services.deserializer.IDeserializer;
import com.grapecity.debugrank.javalib.util.ObserverAdapter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by chrisr on 3/28/2016.
 */
public class GithubCodeRetrofitWebClientTests extends BaseRetrofitWebClientTestCase
{
    private IWebClient<List<CodeLine>, LanguagePuzzle> webClient;

    IDeserializer deserializer;

    @Before
    public void init()
    {
        deserializer = Mockito.mock(IDeserializer.class);
        webClient = new GithubCodeRetrofitWebClient(super.getRetrofit(), deserializer);
    }

    @Test
    public void executeQuery()
    {
        final String EXPECTED_QUERY = String.format("http://www.google.com/%s/%s/%s/data/puzzles/java_8/basic_operator.java", Api.GITHUB_API_OWNER, Api.GITHUB_API_REPO, Api.GITHUB_API_BRANCH);

        ProgrammingLanguage language = new ProgrammingLanguage("java_8", "Java 8", ".java", 1);
        Puzzle puzzle = new Puzzle("basic_operator", "Basic Operator", 1, 2, 3, new String[]{"testcase"}, new String[]{"answer"});

        Observable<List<CodeLine>> observable = webClient.enqueue(new LanguagePuzzle(language, puzzle));

        observable.subscribe();

        //TODO: does this really test it?  It's possible the web client could alter the collection after deserializer is called...
        //test to make sure the deserializer is actually called to return code lines
        verify(deserializer, times(1)).deserializeCodeLines((String) Mockito.any());

        //test to make sure the retrofit url is built correctly with correct values
        assertEquals(EXPECTED_QUERY, fullQuery);
    }
}
