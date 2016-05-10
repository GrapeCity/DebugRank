package com.grapecity.debugrank.javalib.ui.languages;

import com.grapecity.debugrank.javalib.services.repo.IDataRepository;
import com.grapecity.debugrank.javalib.services.repo.IUserRepository;
import com.grapecity.debugrank.javalib.ui.base.BaseRepositoryInteractor;


/**
 * Created by chrisr on 2/29/2016.
 */
public class LanguagesInteractorImpl extends BaseRepositoryInteractor implements ILanguagesInteractor
{
    public LanguagesInteractorImpl(IDataRepository dataRepository, IUserRepository userRepository)
    {
        super(dataRepository, userRepository);
    }
}
