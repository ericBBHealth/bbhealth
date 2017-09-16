package com.jsonapp.ericbbhealth.jsonapp.DetailFeature;

import com.jsonapp.ericbbhealth.jsonapp.Api.Models.CommentsModel;
import com.jsonapp.ericbbhealth.jsonapp.Api.Models.DetailPostModel;
import com.jsonapp.ericbbhealth.jsonapp.Api.Models.PostModel;
import com.jsonapp.ericbbhealth.jsonapp.Api.Models.UsersModel;
import com.jsonapp.ericbbhealth.jsonapp.Api.Services.Implementation.ApiService;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by eric_ on 16/09/2017.
 */

public class PostDetailPresenter implements PostDetailContract.Presenter {

    private PostDetailContract.View view;
    private ApiService apiService;
    private DetailPostModel detailPostModel = new DetailPostModel();
    private PostModel mPostModel;

    public PostDetailPresenter(ApiService apiService, PostDetailContract.View view) {
        this.apiService= apiService;
        this.view = view;
    }

    public void getPostDetails() {
        Observable.zip(apiService.getCommentsByPostId(String.valueOf(mPostModel.getId())), apiService.getUsers(),
                (Func2<List<CommentsModel>, List<UsersModel>, Object>) (commentsModels, usersModels) -> {
                    for (UsersModel usermodel : usersModels) {
                        if (usermodel.getId() == mPostModel.getUserId()) {
                            detailPostModel.setUsername(String.valueOf(usermodel.getId()));
                        }
                    }
                    detailPostModel.setTitle(mPostModel.getTitle());
                    detailPostModel.setBody(mPostModel.getBody());
                    detailPostModel.setNoOfComments(commentsModels.size());

                    return detailPostModel;
                })
        .doOnError(throwable -> throwable.printStackTrace())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnCompleted(() -> view.bindDetails(detailPostModel))

        .subscribe();

    }

    @Override
    public void setPostData(PostModel postModel) {
        mPostModel = postModel;
    }


}
