package com.grapecity.debugrank.javalib.services.compile;

import java.util.ArrayList;
import java.util.List;

import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.HackerRankResult;
import com.grapecity.debugrank.javalib.entities.Puzzle;
import com.grapecity.debugrank.javalib.entities.Result;
import com.grapecity.debugrank.javalib.entities.TestCaseResult;
import com.grapecity.debugrank.javalib.services.deserializer.DeserializerImpl;
import com.grapecity.debugrank.javalib.services.log.BaseLoggedService;
import com.grapecity.debugrank.javalib.services.log.ILogger;
import com.grapecity.debugrank.javalib.util.StringUtil;

/**
 * Created by chrisr on 2/29/2016.
 */
public class HackerRankCompilerApi extends BaseLoggedService implements ICompilerApi
{
    private static final String TAG = HackerRankCompilerApi.class.getName();

    public HackerRankCompilerApi(ILogger logger)
    {
        super(logger);
    }

    @Override
    public String getUriSafeTestCases(Puzzle puzzle)
    {
        try
        {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("[");

            for (int i = 0; i < puzzle.testcases.length; i++)
            {
                String testCase = puzzle.testcases[i];

                stringBuilder.append("\"");
                stringBuilder.append(testCase);
                stringBuilder.append("\"");

                if (i != puzzle.testcases.length - 1)
                {
                    stringBuilder.append(",");
                }
            }

            stringBuilder.append("]");

            return stringBuilder.toString();
        } catch (Exception e)
        {
            logger.error(TAG, e.getMessage());
        }

        return "";
    }

    @Override
    public String getUriSafeCode(List<CodeLine> list)
    {
        try
        {
            StringBuilder stringBuilder = new StringBuilder();

            for (CodeLine codeLine : list)
            {
                String codeText = codeLine.getCodeText();

                //remove all leading and trailing tab characters
                codeText = StringUtil.removeLeadingTrailingCharacter(codeText, "\t");

                //remove all leading and trailing newline characters
                codeText = StringUtil.removeLeadingTrailingCharacter(codeText, "\n");

                stringBuilder.append(codeText);
                stringBuilder.append(System.getProperty("line.separator"));
            }

            return stringBuilder.toString();

        } catch (Exception e)
        {
            logger.error(TAG, e.getMessage());
        }

        return "";

    }

    @Override
    public List<TestCaseResult> getTestCaseResults(HackerRankResult hackerRankResult, Puzzle puzzle)
    {
        List<TestCaseResult> testCaseResults = new ArrayList<>();

        try
        {
            Result result = hackerRankResult.getResult();

            //valid code
            if(result.getStderr() != null && result.getStderr().length > 0)
            {
                for (int i = 0; i < result.getStderr().length; i++)
                {
                    String output = "";

                    if (result.getStdout() != null && result.getStdout().length > i)
                    {
                        output = result.getStdout()[i].trim().replaceAll("\n", "");
                    }

                    String[] inputArray = puzzle.testcases[i].split(",");
                    String expectedOutput = puzzle.answers[i];
                    String message = result.getStderr()[i];

                    testCaseResults.add(new TestCaseResult(i + 1, inputArray, output, expectedOutput, message));
                }
            }
            //if true then compile error
            else
            {
                testCaseResults.add(new TestCaseResult(result.getCompilemessage()));
            }
        }
        catch (Exception e)
        {
            logger.error(TAG, e.getMessage());
        }

        return testCaseResults;
    }
}
