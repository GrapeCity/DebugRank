package com.grapecity.debugrank.javalib.services.web;

import com.grapecity.debugrank.javalib.services.web.IWebClient;

import retrofit2.Retrofit;

/**
 * Created by chrisr on 3/29/2016.
 */
public abstract class RetrofitWebClient<T, E> implements IWebClient<T, E>
{
    protected final Retrofit retrofit;

    protected RetrofitWebClient(Retrofit retrofit)
    {
        this.retrofit = retrofit;
    }
}
