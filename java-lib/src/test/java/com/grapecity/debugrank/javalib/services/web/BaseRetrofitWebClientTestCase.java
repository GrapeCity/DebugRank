package com.grapecity.debugrank.javalib.services.web;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chrisripple on 4/17/16.
 */
public abstract class BaseRetrofitWebClientTestCase
{
    String fullQuery = null;

    protected Retrofit getRetrofit()
    {
        final Interceptor fakeIntercepter = new Interceptor()
        {
            @Override
            public Response intercept(Chain chain) throws IOException
            {
                Response response = null;

                fullQuery = chain.request().url().toString();

                String responseString = "insert json here if needed";

                response = new Response.Builder()
                        .code(200)
                        .message(responseString)
                        .request(chain.request())
                        .protocol(Protocol.HTTP_1_0)
                        .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                        .addHeader("content-type", "application/json")
                        .build();

                return response;
            }
        };

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(fakeIntercepter).build();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        Gson gson = gsonBuilder.create();

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .baseUrl("http://www.google.com")
                .build();
    }
}
