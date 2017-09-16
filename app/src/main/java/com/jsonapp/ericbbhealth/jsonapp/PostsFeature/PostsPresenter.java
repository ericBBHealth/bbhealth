package com.jsonapp.ericbbhealth.jsonapp.PostsFeature;

import android.os.Handler;

import com.jsonapp.ericbbhealth.jsonapp.Api.Models.PostModel;
import com.jsonapp.ericbbhealth.jsonapp.Api.Services.Implementation.ApiService;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by eric_ on 16/09/2017.
 */


public class PostsPresenter implements PostsContract.Presenter {

    private final ApiService apiService;
    private final PostsContract.View view;
    private List<PostModel> postResultList;

    public PostsPresenter(ApiService apiService, PostsContract.View view) {
        this.apiService = apiService;
        this.view = view;
    }

    @Override
    public void getPosts() {

        apiService.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<PostModel>>() {
                    @Override
                    public void onCompleted() {
                        view.setupAdapter(postResultList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        view.setError();
                    }

                    @Override
                    public void onNext(List<PostModel> postModels) {
                        postResultList = postModels;
                    }
                });
    }

}