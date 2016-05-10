package com.grapecity.debugrank.javalib.services.deserializer;

import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.services.log.BaseLoggedService;
import com.grapecity.debugrank.javalib.services.log.ILogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chrisr on 3/29/2016.
 */
public class DeserializerImpl extends BaseLoggedService implements IDeserializer
{
    private static final String TAG = DeserializerImpl.class.getName();

    public DeserializerImpl(ILogger logger)
    {
        super(logger);
    }

    @Override
    public List<CodeLine> deserializeCodeLines(String rawData)
    {
        List<CodeLine> list = new ArrayList<>();

        try
        {
            BufferedReader reader = new BufferedReader(new StringReader(rawData));

            String line;

            while ((line = reader.readLine()) != null)
            {
                list.add(new CodeLine(list.size() + 1, line));
            }
        } catch (IOException exception)
        {
            logger.error(TAG, exception.getMessage());
        }

        return list;
    }
}
