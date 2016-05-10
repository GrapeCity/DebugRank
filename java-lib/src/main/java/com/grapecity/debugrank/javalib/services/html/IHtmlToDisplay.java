package com.grapecity.debugrank.javalib.services.html;

/**
 * Created by chrisripple on 4/29/16.
 */
public interface IHtmlToDisplay<T>
{
    public T convertHtmlToDisplayType(String htmlString, String normalString);
}
