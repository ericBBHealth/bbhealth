package com.jsonapp.ericbbhealth.jsonapp.Di;

import com.jsonapp.ericbbhealth.jsonapp.Api.Services.Implementation.ApiService;
import com.jsonapp.ericbbhealth.jsonapp.PostsFeature.PostsContract;
import com.jsonapp.ericbbhealth.jsonapp.PostsFeature.PostsPresenter;

import dagger.Module;
import dagger.Provides;
/**
 * Created by eric_ on 16/09/2017.
 */

@Module
public class PostsViewModule {

    public PostsContract.View view;

    public PostsViewModule(PostsContract.View view) {
        this.view = view;
    }

    @Provides
    public PostsContract.Presenter provideSearchMediaPresenter(ApiService apiService) {

        return new PostsPresenter(apiService, view);
    };
}
