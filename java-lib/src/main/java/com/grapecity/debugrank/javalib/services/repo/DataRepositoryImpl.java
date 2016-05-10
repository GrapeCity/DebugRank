package com.grapecity.debugrank.javalib.services.repo;

import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.LanguagePuzzle;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.services.web.IWebClient;

import java.util.List;

import rx.Observable;

/**
 * Created by chrisr on 3/29/2016.
 */
public class DataRepositoryImpl implements IDataRepository
{
    private final IWebClient<List<ProgrammingLanguage>, Void> languagesWebClient;
    private final IWebClient<List<Puzzle>, ProgrammingLanguage> puzzlesWebClient;
    private final IWebClient<List<CodeLine>, LanguagePuzzle> codeFileWebClient;

    public DataRepositoryImpl(IWebClient<List<ProgrammingLanguage>, Void> languagesWebClient, IWebClient<List<Puzzle>, ProgrammingLanguage> puzzlesWebClient, IWebClient<List<CodeLine>, LanguagePuzzle> codeFileWebClient)
    {
        this.languagesWebClient = languagesWebClient;
        this.puzzlesWebClient = puzzlesWebClient;
        this.codeFileWebClient = codeFileWebClient;
    }

    @Override
    public Observable<List<ProgrammingLanguage>> loadProgrammingLanguages()
    {
        return this.languagesWebClient.enqueue(null);
    }

    @Override
    public Observable<List<Puzzle>> loadPuzzles(ProgrammingLanguage language)
    {
        return this.puzzlesWebClient.enqueue(language);
    }

    @Override
    public Observable<List<CodeLine>> loadCodeFile(ProgrammingLanguage language, Puzzle puzzle)
    {
        return this.codeFileWebClient.enqueue(new LanguagePuzzle(language, puzzle));
    }
}
