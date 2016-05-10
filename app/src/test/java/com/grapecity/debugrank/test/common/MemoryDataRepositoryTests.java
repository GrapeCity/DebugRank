package com.grapecity.debugrank.test.common;

import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.util.ObserverAdapter;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by chrisripple on 4/24/16.
 */
public class MemoryDataRepositoryTests
{
    MemoryDataRepository repository;

    @Before
    public void init()
    {
        repository = new MemoryDataRepository();
    }

    @Test
    public void loadProgrammingLanguages()
    {
        final List<Integer> listSize = new ArrayList<>();

        repository.loadProgrammingLanguages().subscribe(new ObserverAdapter<List<ProgrammingLanguage>>()
        {
            @Override
            public void onNext(List<ProgrammingLanguage> programmingLanguages)
            {
                listSize.add(programmingLanguages.size());
            }
        });

        assertEquals(1, (int) listSize.get(0));
    }

    @Test
    public void loadPuzzles()
    {
        final List<Integer> listSize = new ArrayList<>();

        repository.loadPuzzles(null).subscribe(new ObserverAdapter<List<Puzzle>>()
        {
            @Override
            public void onNext(List<Puzzle> puzzle)
            {
                listSize.add(puzzle.size());
            }
        });

        assertEquals(1, (int) listSize.get(0));
    }

    @Test
    public void loadCodeFile()
    {
        final List<Integer> listSize = new ArrayList<>();

        repository.loadCodeFile(null, null).subscribe(new ObserverAdapter<List<CodeLine>>()
        {
            @Override
            public void onNext(List<CodeLine> puzzle)
            {
                listSize.add(puzzle.size());
            }
        });

        assertEquals(1, (int) listSize.get(0));
    }
}
