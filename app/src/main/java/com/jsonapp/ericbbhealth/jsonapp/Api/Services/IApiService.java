package com.jsonapp.ericbbhealth.jsonapp.Api.Services;

import com.jsonapp.ericbbhealth.jsonapp.Api.ApiConstants;
import com.jsonapp.ericbbhealth.jsonapp.Api.Models.CommentsModel;
import com.jsonapp.ericbbhealth.jsonapp.Api.Models.PostModel;
import com.jsonapp.ericbbhealth.jsonapp.Api.Models.UsersModel;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by eric_ on 16/09/2017.
 */

public interface IApiService {

    @GET(ApiConstants.API_POST_URL)
    Observable<List<PostModel>> getPost();

    @GET(ApiConstants.API_USERS_URL)
    Observable<List<UsersModel>> getUsers();

    @GET(ApiConstants.API_POST_URL + "/{postId}/" + ApiConstants.API_COMMENTS_URL)
    Observable<List<CommentsModel>> getCommentsByPostId(@Path("postId") String postId);
}
