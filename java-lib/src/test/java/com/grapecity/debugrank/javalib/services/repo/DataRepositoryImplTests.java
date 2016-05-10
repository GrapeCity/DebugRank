package com.grapecity.debugrank.javalib.services.repo;

import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.LanguagePuzzle;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.services.web.IWebClient;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import rx.Observable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.*;

/**
 * Created by chrisripple on 4/17/16.
 */
public class DataRepositoryImplTests
{
    IDataRepository dataRepository;

    IWebClient<List<ProgrammingLanguage>, Void> languagesWebClient;
    IWebClient<List<Puzzle>, ProgrammingLanguage> puzzlesWebClient;
    IWebClient<List<CodeLine>, LanguagePuzzle > codeFileWebClient;

    @Before
    public void init()
    {
        languagesWebClient = mock(IWebClient.class);
        puzzlesWebClient = mock(IWebClient.class);
        codeFileWebClient = mock(IWebClient.class);

        dataRepository = new DataRepositoryImpl(languagesWebClient, puzzlesWebClient, codeFileWebClient);
    }

    @Test
    public void loadProgrammingLanguages()
    {
        dataRepository.loadProgrammingLanguages();

        //verify that the data repo calls the web clients for data
        verify(languagesWebClient, times(1)).enqueue(null);
    }

    @Test
    public void  loadPuzzles()
    {
        ProgrammingLanguage language = new ProgrammingLanguage("java_8", "Java 8", ".java", 1);

        dataRepository.loadPuzzles(language);

        //verify that the data repo calls the web clients for data
        verify(puzzlesWebClient, times(1)).enqueue(language);
    }

    @Test
    public void  loadCodeFile()
    {
        ProgrammingLanguage language = new ProgrammingLanguage("java_8", "Java 8", ".java", 1);
        Puzzle puzzle = new Puzzle("basic_operator", "Basic Operator", 1, 2, 3, new String[]{"testcase"}, new String[]{"answer"});

        dataRepository.loadCodeFile(language, puzzle);

        //verify that the data repo calls the web clients for data
        verify(codeFileWebClient, times(1)).enqueue((LanguagePuzzle) Mockito.any());
    }
}
