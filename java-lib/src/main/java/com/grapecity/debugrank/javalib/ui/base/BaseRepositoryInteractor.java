package com.grapecity.debugrank.javalib.ui.base;

import com.grapecity.debugrank.javalib.entities.AggregatedBugsPoints;
import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.CompletedPuzzle;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.services.repo.IDataRepository;
import com.grapecity.debugrank.javalib.services.repo.IUserRepository;

import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * Created by Ripple on 3/1/2016.
 */
public abstract class BaseRepositoryInteractor implements IDataRepository, IUserRepository
{
    protected final IDataRepository dataRepository;
    protected final IUserRepository userRepository;

    protected BaseRepositoryInteractor(IDataRepository dataRepository, IUserRepository userRepository)
    {
        this.dataRepository = dataRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Observable<List<ProgrammingLanguage>> loadProgrammingLanguages()
    {
        return dataRepository.loadProgrammingLanguages();
    }

    @Override
    public Observable<List<Puzzle>> loadPuzzles(ProgrammingLanguage language)
    {
        return dataRepository.loadPuzzles(language);
    }

    @Override
    public Observable<List<CodeLine>> loadCodeFile(ProgrammingLanguage language, Puzzle puzzle)
    {
        return dataRepository.loadCodeFile(language, puzzle);
    }

    @Override
    public Observable<AggregatedBugsPoints> loadAggregatedBugsPoints()
    {
        return userRepository.loadAggregatedBugsPoints();
    }

    @Override
    public Observable<AggregatedBugsPoints> loadAggregatedBugsPoints(ProgrammingLanguage programmingLanguage)
    {
        return userRepository.loadAggregatedBugsPoints(programmingLanguage);
    }

    @Override
    public Observable<CompletedPuzzle> saveCompletedPuzzle(ProgrammingLanguage programmingLanguage, Puzzle puzzle)
    {
        return userRepository.saveCompletedPuzzle(programmingLanguage, puzzle);
    }

    @Override
    public Observable<Map<String, CompletedPuzzle>> loadCompletedPuzzles(ProgrammingLanguage programmingLanguage)
    {
        return userRepository.loadCompletedPuzzles(programmingLanguage);
    }
}
