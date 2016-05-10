package com.grapecity.debugrank.javalib.services.repo;

import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;

import java.util.List;

import rx.Observable;

/**
 * Created by chrisr on 3/29/2016.
 */
public interface IDataRepository
{
    Observable<List<ProgrammingLanguage>> loadProgrammingLanguages();

    Observable<List<Puzzle>> loadPuzzles(ProgrammingLanguage language);

    Observable<List<CodeLine>> loadCodeFile(ProgrammingLanguage language, Puzzle puzzle);
}
