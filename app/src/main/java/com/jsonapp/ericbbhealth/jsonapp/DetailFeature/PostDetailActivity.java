package com.jsonapp.ericbbhealth.jsonapp.DetailFeature;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.jsonapp.ericbbhealth.jsonapp.Api.Models.PostModel;
import com.jsonapp.ericbbhealth.jsonapp.R;
import com.jsonapp.ericbbhealth.jsonapp.Util.PostModelUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eric_ on 15/09/2017.
 */

public class PostDetailActivity extends AppCompatActivity {

    public static final String BUNDLE_POST_ID_KEY = "POST_ID_KEY";

    @BindView(R.id.main_content)
    FrameLayout mainView;

    public static Intent getIntent(Context context, PostModel postModel) {

        Intent intent = new Intent(context, PostDetailActivity.class);
        Bundle bundle = new Bundle();
        String postModelJson = PostModelUtil.postModelToJson(postModel);

        intent.putExtra(BUNDLE_POST_ID_KEY, postModelJson);
        intent.putExtras(bundle);

        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_post_detail);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            PostDetailView postDetailView = (PostDetailView) getLayoutInflater().inflate(R.layout.view_post_detail, null);
            mainView.addView(postDetailView);
            postDetailView.setPostDetails(bundle.getString(BUNDLE_POST_ID_KEY));
        }
    }
}
