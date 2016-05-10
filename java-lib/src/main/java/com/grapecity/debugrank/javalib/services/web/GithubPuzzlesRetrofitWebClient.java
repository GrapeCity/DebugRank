package com.grapecity.debugrank.javalib.services.web;

import com.grapecity.debugrank.javalib.constants.Api;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by chrisr on 3/29/2016.
 */
public class GithubPuzzlesRetrofitWebClient extends RetrofitWebClient<List<Puzzle>, ProgrammingLanguage>
{
    public GithubPuzzlesRetrofitWebClient(Retrofit retrofit)
    {
        super(retrofit);
    }

    @Override
    public Observable<List<Puzzle>> enqueue(ProgrammingLanguage params)
    {
        return retrofit.create(GitHub.class)
                .puzzles(Api.GITHUB_API_OWNER , Api.GITHUB_API_REPO, Api.GITHUB_API_BRANCH, params.key);
    }

    private interface GitHub
    {
        @GET("/{owner}/{repo}/{branch}/data/puzzles/{path}.json")
        Observable<List<Puzzle>> puzzles(@Path("owner") String owner,
                                   @Path("repo") String repo,
                                   @Path("branch") String branch,
                                   @Path("path") String path
        );
    }
}
