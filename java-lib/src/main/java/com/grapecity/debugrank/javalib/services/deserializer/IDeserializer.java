package com.grapecity.debugrank.javalib.services.deserializer;

import com.grapecity.debugrank.javalib.entities.CodeLine;

import java.util.List;

/**
 * Created by chrisr on 3/29/2016.
 */
public interface IDeserializer
{
    List<CodeLine> deserializeCodeLines(String rawData);
}
