package com.jsonapp.ericbbhealth.jsonapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * Created by eric_ on 16/09/2017.
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main)
    RelativeLayout mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        getLayoutInflater().inflate(R.layout.view_posts, mainView, true);
    }
}
