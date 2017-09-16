package com.jsonapp.ericbbhealth.jsonapp.Application;

import android.app.Application;

import com.jsonapp.ericbbhealth.jsonapp.Di.ApiModule;
import com.jsonapp.ericbbhealth.jsonapp.Di.DaggerJsonAppApplicationComponent;
import com.jsonapp.ericbbhealth.jsonapp.Di.JsonAppApplicationComponent;

/**
 * Created by eric_ on 16/09/2017.
 */


public class JsonAppApplication extends Application {

    private JsonAppApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerJsonAppApplicationComponent.builder().apiModule(new ApiModule()).build();

    }

    public JsonAppApplicationComponent getComponent() {
        return applicationComponent;
    }
}
