package com.jsonapp.ericbbhealth.jsonapp.Di;

import com.jsonapp.ericbbhealth.jsonapp.Api.Services.Implementation.ApiService;
import com.jsonapp.ericbbhealth.jsonapp.DetailFeature.PostDetailContract;
import com.jsonapp.ericbbhealth.jsonapp.DetailFeature.PostDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by eric_ on 16/09/2017.
 */

@Module
public class PostDetailModule {

    public PostDetailContract.View view;

    public PostDetailModule(PostDetailContract.View view) {
        this.view = view;
    }

    @Provides
    public PostDetailContract.Presenter providePostDetailPresenter(ApiService apiService) {

        return new PostDetailPresenter(apiService, view);
    };
}
