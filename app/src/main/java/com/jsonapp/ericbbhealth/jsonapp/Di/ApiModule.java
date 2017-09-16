package com.jsonapp.ericbbhealth.jsonapp.Di;

import com.jsonapp.ericbbhealth.jsonapp.Api.ApiConstants;
import com.jsonapp.ericbbhealth.jsonapp.Api.Services.Implementation.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eric_ on 16/09/2017.
 */

@Module
public class ApiModule {

    @Provides
    @Singleton
    public ApiService provideSearchService() {

        return new ApiService(getDefaultAdapter());
    }

    private Retrofit getDefaultAdapter () {
        return new Retrofit.Builder()
                .baseUrl(ApiConstants.API_BASE_URL)
                .client(getHttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient getHttpClient() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
                .build();

    }
}
