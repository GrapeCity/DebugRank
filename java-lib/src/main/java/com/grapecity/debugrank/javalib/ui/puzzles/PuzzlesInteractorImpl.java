package com.grapecity.debugrank.javalib.ui.puzzles;

import com.grapecity.debugrank.javalib.services.repo.IDataRepository;
import com.grapecity.debugrank.javalib.services.repo.IUserRepository;
import com.grapecity.debugrank.javalib.ui.base.BaseRepositoryInteractor;


/**
 * Created by chrisr on 2/29/2016.
 */
public class PuzzlesInteractorImpl extends BaseRepositoryInteractor implements IPuzzlesInteractor
{
    public PuzzlesInteractorImpl(IDataRepository dataRepository, IUserRepository userRepository)
    {
        super(dataRepository, userRepository);
    }
}