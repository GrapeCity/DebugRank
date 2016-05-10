package com.grapecity.debugrank.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.ui.base.IGetLanguage;
import com.grapecity.debugrank.javalib.ui.base.IGetPuzzle;


/**
 * Created by chrisr on 2/29/2016.
 */
public abstract class BaseFragment extends Fragment implements IGetLanguage, IGetPuzzle
{
    @Nullable
    @InjectExtra
    protected ProgrammingLanguage language;

    @Nullable
    @InjectExtra
    protected Puzzle puzzle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Dart.inject(this, getActivity());
    }

    @Override
    public ProgrammingLanguage getLanguage()
    {
        return language;
    }

    @Override
    public Puzzle getPuzzle()
    {
        return puzzle;
    }
}
