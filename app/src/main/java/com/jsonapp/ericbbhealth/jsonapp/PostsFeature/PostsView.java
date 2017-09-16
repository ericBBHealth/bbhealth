package com.jsonapp.ericbbhealth.jsonapp.PostsFeature;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jsonapp.ericbbhealth.jsonapp.Api.Models.PostModel;
import com.jsonapp.ericbbhealth.jsonapp.Application.JsonAppApplication;
import com.jsonapp.ericbbhealth.jsonapp.Di.DaggerPostsComponent;
import com.jsonapp.ericbbhealth.jsonapp.Di.PostsComponent;
import com.jsonapp.ericbbhealth.jsonapp.Di.PostsViewModule;
import com.jsonapp.ericbbhealth.jsonapp.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eric_ on 16/09/2017.
 */

public class PostsView extends LinearLayout implements PostsContract.View {

    @Inject
    PostsContract.Presenter presenter;

    @BindView(R.id.search_results)
    RecyclerView postsResults;

    @BindView(R.id.progress_bar)
    ProgressBar spinner;

    PostsResultCardAdapter postsResultCardAdapter;
    private LinearLayoutManager mLayoutManager;

    public PostsView(Context context) {
        super(context);
    }

    public PostsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PostsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        JsonAppApplication application = (JsonAppApplication) getContext().getApplicationContext();

        PostsComponent postsComponent = DaggerPostsComponent.builder()
                .jsonAppApplicationComponent(application.getComponent())
                .postsViewModule(new PostsViewModule(this)).build();

        postsComponent.inject(this);

        ButterKnife.bind(this);

        mLayoutManager = new LinearLayoutManager(getContext());
        postsResults.setLayoutManager(mLayoutManager);

        presenter.getPosts();
    }


    @Override
    public void setupAdapter(List<PostModel> postsResult) {
        hideLoading();

        if (postsResultCardAdapter == null) {
            postsResultCardAdapter = new PostsResultCardAdapter(postsResult);
        }

        postsResults.setAdapter(postsResultCardAdapter);

    }

    @Override
    public void hideLoading() {
        spinner.setVisibility(GONE);
    }

    @Override
    public void setError() {
        String error = "error";
        //TODO: Error on recyclerview
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

}
