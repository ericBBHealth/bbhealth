package com.jsonapp.ericbbhealth.jsonapp.PostsFeature;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jsonapp.ericbbhealth.jsonapp.Api.Models.PostModel;
import com.jsonapp.ericbbhealth.jsonapp.DetailFeature.PostDetailActivity;
import com.jsonapp.ericbbhealth.jsonapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * Created by eric_ on 16/09/2017.
 */

public class PostsResultCardAdapter extends RecyclerView.Adapter<PostsResultCardAdapter.ViewHolder> {

    private List<PostModel> postsResult;

    public PostsResultCardAdapter(@NonNull List<PostModel> postsResult) {
        this.postsResult = postsResult;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_post_card, parent, false);
        ViewHolder viewholder = new ViewHolder(v);

        return  viewholder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(postsResult.get(position));
    }

    @Override
    public int getItemCount() {
        return postsResult.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_container)
        View container;

        @BindView(R.id.post_card_title)
        TextView cardBody;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(PostModel postModel) {
            cardBody.setText(postModel.getTitle());
            container.setOnClickListener(view -> {
                Context context = view.getContext();
                Intent intent = PostDetailActivity.getIntent(context, postModel);
                context.startActivity(intent);
            });

        }
    }
}
