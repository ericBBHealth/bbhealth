package com.jsonapp.ericbbhealth.jsonapp.Util;

import com.google.gson.Gson;
import com.jsonapp.ericbbhealth.jsonapp.Api.Models.PostModel;

/**
 * Created by eric_ on 16/09/2017.
 */


public class PostModelUtil {

    public static String postModelToJson(PostModel postModel) {
        Gson gson = new Gson();

        return gson.toJson(postModel);
    }

    public static PostModel jsonToPostModel(String json) {
        Gson gson = new Gson();

        return gson.fromJson(json, PostModel.class);
    }

}
