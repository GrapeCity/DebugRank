package com.grapecity.debugrank.injection.module;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.HackerRankCompileParams;
import com.grapecity.debugrank.javalib.entities.HackerRankResult;
import com.grapecity.debugrank.javalib.entities.LanguagePuzzle;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import com.grapecity.debugrank.javalib.services.deserializer.DeserializerImpl;
import com.grapecity.debugrank.javalib.services.deserializer.IDeserializer;
import com.grapecity.debugrank.javalib.services.log.ILogger;
import com.grapecity.debugrank.javalib.services.web.IWebClient;

import java.util.List;

import com.grapecity.debugrank.services.image.IImageLoadingService;
import com.grapecity.debugrank.services.image.PicassoGithubImageLoadingService;
import com.grapecity.debugrank.javalib.services.web.GithubCodeRetrofitWebClient;
import com.grapecity.debugrank.javalib.services.web.GithubLanguagesRetrofitWebClient;
import com.grapecity.debugrank.javalib.services.web.GithubPuzzlesRetrofitWebClient;
import com.grapecity.debugrank.javalib.services.web.HackerRankRetrofitWebClient;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;

@Module(includes = AppModule.class)
public class NetModule
{
    String mBaseUrl;
    String mBaseUrl2;

    // Constructor needs one parameter to instantiate.
    public NetModule(String baseUrl, String baseUrl2)
    {
        this.mBaseUrl = baseUrl;
        this.mBaseUrl2 = baseUrl2;
    }

    @Provides
    @Singleton
    Gson provideGson()
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application)
    {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache)
    {
        return new OkHttpClient.Builder().cache(cache).build();
    }

    @Provides
    @Named("retrofit")
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    @Named("retrofit2")
    @Singleton
    Retrofit provideRetrofit2(Gson gson, OkHttpClient okHttpClient)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl2)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    @Named("retrofitRaw")
    @Singleton
    Retrofit provideRetrofitRaw(OkHttpClient okHttpClient)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    IDeserializer providesDeserializer(ILogger logger)
    {
        return new DeserializerImpl(logger);
    }

    @Provides
    @Singleton
    IWebClient<List<CodeLine>, LanguagePuzzle> providesCodeWebClient(@Named("retrofitRaw") Retrofit retrofitRaw, IDeserializer deserializer)
    {
        return new GithubCodeRetrofitWebClient(retrofitRaw, deserializer);
    }

    @Provides
    @Singleton
    IWebClient<List<ProgrammingLanguage>, Void> providesLanguagesWebClient(@Named("retrofit") Retrofit retrofit)
    {
        return new GithubLanguagesRetrofitWebClient(retrofit);
    }

    @Provides
    @Singleton
    IWebClient<List<Puzzle>, ProgrammingLanguage> providesPuzzlesWebClient(@Named("retrofit") Retrofit retrofit)
    {
        return new GithubPuzzlesRetrofitWebClient(retrofit);
    }

    @Provides
    @Singleton
    IWebClient<HackerRankResult, HackerRankCompileParams> providesHackerRankWebClient(@Named("retrofit2") Retrofit retrofit)
    {
        return new HackerRankRetrofitWebClient(retrofit);
    }

    @Provides
    @Singleton
    IImageLoadingService providesImageLoadingService()
    {
        return new PicassoGithubImageLoadingService();
    }
}