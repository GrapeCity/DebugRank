package com.grapecity.debugrank.injection.module;

import android.app.Application;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.grapecity.debugrank.javalib.entities.HackerRankCompileParams;
import com.grapecity.debugrank.javalib.entities.HackerRankResult;
import com.grapecity.debugrank.javalib.entities.Result;
import com.grapecity.debugrank.javalib.services.web.IWebClient;

import javax.inject.Named;

import com.grapecity.debugrank.TestMyApp;
import com.grapecity.debugrank.services.image.IImageLoadingService;
import com.grapecity.debugrank.test.common.StaticProperties;
import com.grapecity.debugrank.util.MockDrawable;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import rx.Observable;

public class TestNetModule extends NetModule
{
    private final TestMyApp app;

    public TestNetModule(TestMyApp app, String baseUrl, String baseUrl2)
    {
        super(baseUrl, baseUrl2);

        this.app = app;
    }

    @Override
    Gson provideGson()
    {
        return new Gson();
    }

    @Override
    Cache provideOkHttpCache(Application application)
    {
        return new Cache(null, 1);
    }

    @Override
    OkHttpClient provideOkHttpClient(Cache cache)
    {
        return new OkHttpClient();
    }

    @Override
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient)
    {
       return new Retrofit.Builder().baseUrl("http://www.google.com").build();
    }

    @Override
    Retrofit provideRetrofit2(Gson gson, OkHttpClient okHttpClient)
    {
        return new Retrofit.Builder().baseUrl("http://www.google.com").build();
    }


    @Override
    Retrofit provideRetrofitRaw(OkHttpClient okHttpClient)
    {
        return new Retrofit.Builder().baseUrl("http://www.google.com").build();
    }

    @Override
    IImageLoadingService providesImageLoadingService()
    {
        return new IImageLoadingService()
        {
            @Override
            public void loadLanguageImage(ImageView imageView, String languageKey)
            {
                MockDrawable mockDrawable = new MockDrawable();
                mockDrawable.setTag(languageKey);

                //setup a mock drawable with a tag property so we can assert that the view adapters
                //actually try and set the image using this service
                imageView.setImageDrawable(mockDrawable);
            }

            @Override
            public void setDrawable(ImageView imageView, @DrawableRes int resourceId)
            {
                MockDrawable mockDrawable = new MockDrawable();

                mockDrawable.setTag(resourceId);

                imageView.setImageDrawable(mockDrawable);
            }
        };
    }

    @Override
    IWebClient<HackerRankResult, HackerRankCompileParams> providesHackerRankWebClient(@Named("retrofit2") Retrofit retrofit)
    {
        return new TestHackerRankWebClient(app);
    }

    private class TestHackerRankWebClient implements IWebClient<HackerRankResult, HackerRankCompileParams>
    {
        private final TestMyApp app;

        public TestHackerRankWebClient(TestMyApp app)
        {
            this.app = app;
        }

        @Override
        public Observable<HackerRankResult> enqueue(HackerRankCompileParams params)
        {
            if(!StaticProperties.getInstance().isInternetUnableToReach())
            {
                HackerRankResult hackerRankResult = new HackerRankResult();

                Result result = new Result();

                //pass the test cases
                if (StaticProperties.getInstance().isPassAllTestCases())
                {
                    result.setStdout(new String[]{"answer passed", "answer passed"});
                }
                //fail the second test cases
                else
                {
                    result.setStdout(new String[]{"answer passed", "answer failed"});
                }

                //code is "compiled" successfully
                if (StaticProperties.getInstance().isCodeCompilesSuccessfully())
                {
                    result.setStderr(new String[]{"hello msg", "hello msg"});
                }
                //else code failed to compile
                else
                {
                    result.setCompilemessage("compile error");
                }

                hackerRankResult.setResult(result);

                return Observable.just(hackerRankResult);
            }

            return Observable.error(new Exception());
        }
    }
}