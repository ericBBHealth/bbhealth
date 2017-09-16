package com.jsonapp.ericbbhealth.jsonapp.DetailFeature;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jsonapp.ericbbhealth.jsonapp.Api.Models.DetailPostModel;
import com.jsonapp.ericbbhealth.jsonapp.Api.Models.PostModel;
import com.jsonapp.ericbbhealth.jsonapp.Application.JsonAppApplication;
import com.jsonapp.ericbbhealth.jsonapp.Di.DaggerPostsDetailComponent;
import com.jsonapp.ericbbhealth.jsonapp.Di.PostDetailModule;
import com.jsonapp.ericbbhealth.jsonapp.Di.PostsDetailComponent;
import com.jsonapp.ericbbhealth.jsonapp.R;
import com.jsonapp.ericbbhealth.jsonapp.Util.PostModelUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eric_ on 16/09/2017.
 */

public class PostDetailView extends LinearLayout implements PostDetailContract.View {

    @Inject
    PostDetailContract.Presenter presenter;

    @BindView(R.id.post_card_title)
    TextView cardTitle;

    @BindView(R.id.post_card_body)
    TextView cardBody;

    @BindView(R.id.post_card_username)
    TextView cardUsername;

    @BindView(R.id.post_card_noofcomments)
    TextView cardNoOfComments;

    private PostModel mPostModel;

    public PostDetailView(Context context) {
        super(context);
    }

    public PostDetailView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PostDetailView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        JsonAppApplication application = (JsonAppApplication) getContext().getApplicationContext();

        PostsDetailComponent postsDetailComponent = DaggerPostsDetailComponent.builder()
                .jsonAppApplicationComponent(application.getComponent())
                .postDetailModule(new PostDetailModule(this)).build();

        postsDetailComponent.inject(this);

        ButterKnife.bind(this);
        presenter.setPostData(mPostModel);
        presenter.getPostDetails();
    }


    @Override
    public void bindDetails(DetailPostModel detailPostModel) {

        cardBody.setText(detailPostModel.getBody());
        cardTitle.setText(detailPostModel.getTitle());
        cardNoOfComments.setText(String.valueOf(detailPostModel.getNoOfComments()));
        cardUsername.setText(detailPostModel.getUsername());
    }

    public void setPostDetails(String json) {
        mPostModel = PostModelUtil.jsonToPostModel(json);
    }
}
