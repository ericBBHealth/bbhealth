package com.jsonapp.ericbbhealth.jsonapp;

import com.jsonapp.ericbbhealth.jsonapp.Api.Models.PostModel;
import com.jsonapp.ericbbhealth.jsonapp.Util.PostModelUtil;

import org.junit.Test;

/**
 * Created by eric_ on 16/09/2017.
 */

public class PostModelUtilTest {

    @Test
    public void postModelToJson() {
        PostModel postModel = new PostModel();

        postModel.setBody("Body");
        postModel.setId(123);
        postModel.setTitle("title");

        String json = PostModelUtil.postModelToJson(postModel);

        assert(json.equalsIgnoreCase("{\"body\":\"Body\",\"id\":123,\"userId\":0}"));
    }

    @Test
    public void jsonToPostModel() {
        String json = "{\"body\":\"Body\",\"id\":123,\"userId\":0}";

        PostModel postModel = PostModelUtil.jsonToPostModel(json);

        assert(postModel.getBody().equalsIgnoreCase("Body"));
        assert(postModel.getUserId() == 0);
        assert(postModel.getId() == 123);
    }
}
