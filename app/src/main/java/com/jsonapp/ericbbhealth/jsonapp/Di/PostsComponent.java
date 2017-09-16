package com.jsonapp.ericbbhealth.jsonapp.Di;

import com.jsonapp.ericbbhealth.jsonapp.Application.ActivityScope;
import com.jsonapp.ericbbhealth.jsonapp.PostsFeature.PostsView;

import dagger.Component;
/**
 * Created by eric_ on 16/09/2017.
 */

@ActivityScope
@Component(dependencies = JsonAppApplicationComponent.class, modules = {PostsViewModule.class})
public interface PostsComponent
{

    void inject(PostsView postsView);
}
