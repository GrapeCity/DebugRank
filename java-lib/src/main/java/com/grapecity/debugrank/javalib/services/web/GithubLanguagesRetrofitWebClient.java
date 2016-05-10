package com.grapecity.debugrank.javalib.services.web;

import com.grapecity.debugrank.javalib.constants.Api;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by chrisr on 3/29/2016.
 */
public class GithubLanguagesRetrofitWebClient extends RetrofitWebClient<List<ProgrammingLanguage>, Void>
{
    public GithubLanguagesRetrofitWebClient(Retrofit retrofit)
    {
        super(retrofit);
    }

    @Override
    public Observable<List<ProgrammingLanguage>> enqueue(Void params)
    {
        return retrofit.create(GitHub.class)
                .languages(Api.GITHUB_API_OWNER , Api.GITHUB_API_REPO, Api.GITHUB_API_BRANCH, Api.GITHUB_API_LANGUAGES_PATH);
    }

    private interface GitHub
    {
        @GET("/{owner}/{repo}/{branch}/{path}")
        Observable<List<ProgrammingLanguage>> languages(@Path("owner") String owner,
                                                  @Path("repo") String repo,
                                                  @Path("branch") String branch,
                                                  @Path(value = "path", encoded = true) String path
        );
    }
}
