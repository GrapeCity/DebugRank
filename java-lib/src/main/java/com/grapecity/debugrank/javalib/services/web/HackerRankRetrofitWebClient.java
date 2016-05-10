package com.grapecity.debugrank.javalib.services.web;

import com.grapecity.debugrank.javalib.constants.Api;
import com.grapecity.debugrank.javalib.entities.HackerRankCompileParams;
import com.grapecity.debugrank.javalib.entities.HackerRankResult;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by chrisr on 3/29/2016.
 */
public class HackerRankRetrofitWebClient extends RetrofitWebClient<HackerRankResult, HackerRankCompileParams>
{
    public HackerRankRetrofitWebClient(Retrofit retrofit)
    {
        super(retrofit);
    }

    @Override
    public Observable<HackerRankResult> enqueue(HackerRankCompileParams params)
    {
        return retrofit.create(HackerRank.class)
                .submitCode(params.code, params.language.code, params.testCases, Api.HACKERRANK_API_KEY);
    }

    private interface HackerRank
    {
        @FormUrlEncoded
        @POST("/checker/submission.json")
        Observable<HackerRankResult> submitCode(@Field("source") String source,
                                          @Field("lang") int lang,
                                          @Field("testcases") String testcases,
                                          @Field("api_key") String apiKey);
    }
}
