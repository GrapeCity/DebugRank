package com.grapecity.debugrank.javalib.ui.solve.code;

import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.services.html.IHtmlToDisplay;
import com.grapecity.debugrank.javalib.services.repo.IDataRepository;
import com.grapecity.debugrank.javalib.services.repo.IUserRepository;
import com.grapecity.debugrank.javalib.services.syntax.ISyntaxHighlighter;
import com.grapecity.debugrank.javalib.ui.solve.result.SolveResultInteractorImpl;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SolveCodeInteractorImplTests
{
    ISolveCodeInteractor interactor;

    ISyntaxHighlighter syntaxHighlighter;
    IHtmlToDisplay<String> htmlToDisplay;

    @Before
    public void init()
    {
        List<CodeLine> list = new ArrayList<>();
        list.add(new CodeLine(1, "this is a line of code;"));

        IDataRepository dataRepository = mock(IDataRepository.class);
        doReturn(Observable.just(list)).when(dataRepository).loadCodeFile(any(ProgrammingLanguage.class), any(Puzzle.class));

        IUserRepository userRepository = mock(IUserRepository.class);

        syntaxHighlighter = mock(ISyntaxHighlighter.class);
        doReturn(null).when(syntaxHighlighter).getSyntaxHighlightedText(anyString(), anyString());

        htmlToDisplay = mock(IHtmlToDisplay.class);
        doReturn("this is a line of code;").when(htmlToDisplay).convertHtmlToDisplayType(anyString(), anyString());

        interactor = new SolveCodeInteractorImpl(dataRepository, userRepository, syntaxHighlighter, htmlToDisplay);
    }

    @Test
    public void loadCodeFile()
    {
        ProgrammingLanguage language = new ProgrammingLanguage("java_8", "Java 8", ".java", 1);

        interactor.loadCodeFile(language, null).subscribe();

        verify(syntaxHighlighter, times(1)).getSyntaxHighlightedText(anyString(), anyString());
    }

    @Test
    public void getSyntaxHighlightedTextCacheContains()
    {
        ProgrammingLanguage language = new ProgrammingLanguage("java_8", "Java 8", ".java", 1);

        interactor.loadCodeFile(language, null).subscribe();

        String valueFromCache = (String) interactor.getSyntaxHighlightedText(null, new CodeLine(1, ""));

        //if only called once then getSyntaxHighlightedText retrieved from the cache
        verify(htmlToDisplay, times(1)).convertHtmlToDisplayType(anyString(), anyString());

        assertEquals("this is a line of code;", valueFromCache);
    }

    @Test
    public void getSyntaxHighlightedTextCacheDoesNotContain()
    {
        ProgrammingLanguage language = new ProgrammingLanguage("java_8", "Java 8", ".java", 1);

        interactor.loadCodeFile(language, null).subscribe();

        doReturn("this is a line of code not from the cache;").when(htmlToDisplay).convertHtmlToDisplayType(anyString(), anyString());

        String valueNotFromCache = (String) interactor.getSyntaxHighlightedText(null, new CodeLine(2, ""));

        //if called twice then the code did not exist in cache
        verify(htmlToDisplay, times(2)).convertHtmlToDisplayType(anyString(), anyString());

        assertEquals("this is a line of code not from the cache;", valueNotFromCache);
    }

    @Test
    public void refreshSyntaxHighlightedTextCache()
    {
        ProgrammingLanguage language = new ProgrammingLanguage("java_8", "Java 8", ".java", 1);

        interactor.loadCodeFile(language, null).subscribe();

        CodeLine codeLine = new CodeLine(1, "this is my updated code line;");

        doReturn("this is my updated code line;").when(htmlToDisplay).convertHtmlToDisplayType(anyString(), anyString());

        interactor.refreshSyntaxHighlightedTextCache(language, codeLine).subscribe();

        verify(syntaxHighlighter, times(2)).getSyntaxHighlightedText(anyString(), anyString());
        verify(htmlToDisplay, times(2)).convertHtmlToDisplayType(anyString(), anyString());

        String valueFromCache = (String) interactor.getSyntaxHighlightedText(null, new CodeLine(1, ""));

        assertEquals("this is my updated code line;", valueFromCache);
    }
}
