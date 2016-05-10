package com.grapecity.debugrank.javalib.services.web;

import rx.Observable;

/**
 *
 * @param <T> The return type when the call is successful.
 * @param <E> The type for the parameter object.
 */
public interface IWebClient<T, E>
{
    Observable<T> enqueue(E params);
}
