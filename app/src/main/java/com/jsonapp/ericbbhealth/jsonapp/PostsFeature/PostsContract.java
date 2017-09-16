package com.jsonapp.ericbbhealth.jsonapp.PostsFeature;

import com.jsonapp.ericbbhealth.jsonapp.Api.Models.PostModel;

import java.util.List;
/**
 * Created by eric_ on 16/09/2017.
 */

public interface PostsContract {

    interface View {

        void hideLoading();

        void setError();

        void setupAdapter(List<PostModel> postsResult);

    }

    interface Presenter {

        void getPosts();

    }
}
