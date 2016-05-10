package com.grapecity.debugrank.test.common;

import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.services.repo.IDataRepository;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by chrisripple on 4/24/16.
 */
public class MemoryDataRepository implements IDataRepository
{
    List<ProgrammingLanguage> languages = new ArrayList<>();
    List<Puzzle> puzzles = new ArrayList<>();
    List<CodeLine> codeLines = new ArrayList<>();

    public MemoryDataRepository()
    {
        languages.add(new ProgrammingLanguage("java_8", "Java 8", ".java", 1));
        languages.add(new ProgrammingLanguage("javascript", "JavaScript", ".js", 1));
        languages.add(new ProgrammingLanguage("csharp", "C#", ".cs", 1));
        languages.add(new ProgrammingLanguage("fsharp", "F#", ".cs", 1));

        puzzles.add(new Puzzle("basic_operator", "Basic Operator", 1, 120, 3, new String[]{"testcase", "testcase"}, new String[]{"answer passed", "answer passed"}));
        puzzles.add(new Puzzle("super_operator", "Super Operator", 4, 180, 6, new String[]{"testcase 2"}, new String[]{"answer 2"}));

        codeLines.add(new CodeLine(1, "this is some code;"));
    }

    @Override
    public Observable<List<ProgrammingLanguage>> loadProgrammingLanguages()
    {
        if(!staticProps().isInternetUnableToReach())
        {
            return Observable.just(languages);
        }

        return Observable.error(new Exception());
    }

    public List<ProgrammingLanguage> getTestProgrammingLanguages()
    {
        return languages;
    }

    public List<Puzzle> getTestPuzzles()
    {
        return puzzles;
    }

    public List<CodeLine> getTestCodeLines()
    {
        return codeLines;
    }

    @Override
    public Observable<List<Puzzle>> loadPuzzles(ProgrammingLanguage language)
    {
        if(!staticProps().isInternetUnableToReach())
        {
            return Observable.just(puzzles);
        }

        return Observable.error(new Exception());
    }

    @Override
    public Observable<List<CodeLine>> loadCodeFile(ProgrammingLanguage language, Puzzle puzzle)
    {
        if(!staticProps().isInternetUnableToReach())
        {
            return Observable.just(codeLines);
        }

        return Observable.error(new Exception());
    }

    protected StaticProperties staticProps()
    {
        return StaticProperties.getInstance();
    }
}
