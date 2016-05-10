package com.grapecity.debugrank.javalib.ui.base;

import com.grapecity.debugrank.javalib.entities.AggregatedBugsPoints;
import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.CompletedPuzzle;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.services.repo.IDataRepository;
import com.grapecity.debugrank.javalib.services.repo.IUserRepository;
import com.grapecity.debugrank.javalib.ui.languages.ILanguagesInteractor;
import com.grapecity.debugrank.javalib.ui.languages.ILanguagesView;
import com.grapecity.debugrank.javalib.ui.languages.LanguagesPresenterImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;

import rx.Observable;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by chrisripple on 4/18/16.
 */
public class BaseRepositoryInteractorTests
{
    BaseRepositoryInteractor interactor;

    IDataRepository dataRepository;
    IUserRepository userRepository;

    @Before
    public void init()
    {
        dataRepository = Mockito.mock(IDataRepository.class);
        userRepository = Mockito.mock(IUserRepository.class);

        interactor = new MockRepositoryInteractor(dataRepository, userRepository);
    }

    @Test
    public void testLoadProgrammingLanguages()
    {
        interactor.loadProgrammingLanguages();

        verify(dataRepository, times(1)).loadProgrammingLanguages();
    }

    @Test
    public void testLoadPuzzles()
    {
        interactor.loadPuzzles(null);

        verify(dataRepository, times(1)).loadPuzzles(null);
    }

    @Test
    public void testLoadCodeFile()
    {
        interactor.loadCodeFile(null, null);

        verify(dataRepository, times(1)).loadCodeFile(null, null);
    }

    @Test
    public void testLoadAggregatedBugsPoints()
    {
        interactor.loadAggregatedBugsPoints();

        verify(userRepository, times(1)).loadAggregatedBugsPoints();
    }

    @Test
    public void testLoadAggregatedBugsPointsForLanguage()
    {
        interactor.loadAggregatedBugsPoints(null);

        verify(userRepository, times(1)).loadAggregatedBugsPoints(null);
    }

    @Test
    public void testSaveCompletedPuzzle()
    {
        interactor.saveCompletedPuzzle(null, null);

        verify(userRepository, times(1)).saveCompletedPuzzle(null, null);
    }

    @Test
    public void testLoadCompletedPuzzles()
    {
        interactor.loadCompletedPuzzles(null);

        verify(userRepository, times(1)).loadCompletedPuzzles(null);
    }

    private class MockRepositoryInteractor extends BaseRepositoryInteractor
    {
        public MockRepositoryInteractor(IDataRepository dataRepository, IUserRepository userRepository)
        {
            super(dataRepository, userRepository);
        }
    }
}