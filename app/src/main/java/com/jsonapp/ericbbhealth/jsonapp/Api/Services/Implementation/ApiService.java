package com.jsonapp.ericbbhealth.jsonapp.Api.Services.Implementation;

import com.jsonapp.ericbbhealth.jsonapp.Api.Models.CommentsModel;
import com.jsonapp.ericbbhealth.jsonapp.Api.Models.PostModel;
import com.jsonapp.ericbbhealth.jsonapp.Api.Models.UsersModel;
import com.jsonapp.ericbbhealth.jsonapp.Api.Services.IApiService;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by eric_ on 16/09/2017.
 */

public class ApiService {

    private IApiService apiService;

    public ApiService(Retrofit retrofit) {
        apiService = retrofit.create(IApiService.class);
    }

    public Observable<List<PostModel>> getPosts() {
        return apiService.getPost();
    }

    public Observable<List<UsersModel>> getUsers() {
        return apiService.getUsers();
    }

    public Observable<List<CommentsModel>> getCommentsByPostId(String postId) {
        return apiService.getCommentsByPostId(postId);
    }
}
