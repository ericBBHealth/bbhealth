package com.jsonapp.ericbbhealth.jsonapp.DetailFeature;

import com.jsonapp.ericbbhealth.jsonapp.Api.Models.DetailPostModel;
import com.jsonapp.ericbbhealth.jsonapp.Api.Models.PostModel;

import java.util.List;

/**
 * Created by eric_ on 16/09/2017.
 */

public class PostDetailContract {


    public interface View {

        void bindDetails(DetailPostModel detailPostModel);

    }

    public interface Presenter {

        void getPostDetails();

        void setPostData(PostModel mPostModel);
    }
}
