package com.jsonapp.ericbbhealth.jsonapp.Di;

import com.jsonapp.ericbbhealth.jsonapp.Api.Services.Implementation.ApiService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by eric_ on 16/09/2017.
 */

@Singleton
@Component(modules = ApiModule.class)
public interface JsonAppApplicationComponent {

    ApiService provideApiService();

}
