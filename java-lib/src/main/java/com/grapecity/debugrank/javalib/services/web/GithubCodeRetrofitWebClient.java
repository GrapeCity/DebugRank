package com.grapecity.debugrank.javalib.services.web;

import com.grapecity.debugrank.javalib.constants.Api;
import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.LanguagePuzzle;
import com.grapecity.debugrank.javalib.services.deserializer.IDeserializer;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by chrisr on 3/29/2016.
 */
public class GithubCodeRetrofitWebClient extends RetrofitWebClient<List<CodeLine>, LanguagePuzzle>
{
    private final IDeserializer deserializer;

    public GithubCodeRetrofitWebClient(Retrofit retrofit, IDeserializer deserializer)
    {
        super(retrofit);

        this.deserializer = deserializer;
    }

    @Override
    public Observable<List<CodeLine>> enqueue(LanguagePuzzle params)
    {
        return retrofit.create(GitHub.class)
                .contentFile(Api.GITHUB_API_OWNER, Api.GITHUB_API_REPO, Api.GITHUB_API_BRANCH, params.language.key, params.puzzle.key, params.language.file)
                .map(new Func1<ResponseBody, String>()
                {
                    @Override
                    public String call(ResponseBody responseBody)
                    {
                        try
                        {
                            return responseBody.string();
                        }
                        catch (IOException e)
                        {
                            //TODO: add logger and force to error
                        }

                        return "";
                    }
                })
                .map(new Func1<String, List<CodeLine>>()
                {
                    @Override
                    public List<CodeLine> call(String body)
                    {
                        return deserializer.deserializeCodeLines(body);
                    }
                });
    }

    private interface GitHub
    {
        @GET("/{owner}/{repo}/{branch}/data/puzzles/{language_key}/{puzzle_key}{file}")
        Observable<ResponseBody> contentFile(@Path("owner") String owner,
                                             @Path("repo") String repo,
                                             @Path("branch") String branch,
                                             @Path("language_key") String languageKey,
                                             @Path("puzzle_key") String puzzleKey,
                                             @Path("file") String file
        );
    }
}
