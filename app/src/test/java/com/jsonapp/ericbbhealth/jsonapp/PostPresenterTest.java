package com.jsonapp.ericbbhealth.jsonapp;

import com.jsonapp.ericbbhealth.jsonapp.Api.Models.PostModel;
import com.jsonapp.ericbbhealth.jsonapp.Api.Services.Implementation.ApiService;
import com.jsonapp.ericbbhealth.jsonapp.PostsFeature.PostsContract;
import com.jsonapp.ericbbhealth.jsonapp.PostsFeature.PostsPresenter;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(JUnit4.class)
public class PostPresenterTest {
    @ClassRule
    public static final RxImmdeiateSchedulerRule overrideRule = new RxImmdeiateSchedulerRule();

    @Mock
    ApiService mockApiService;

    @Mock
    PostsContract.View mockView;

    private PostsPresenter presenter;

    @Before
    public void setup() throws Exception {

        MockitoAnnotations.initMocks(this);

        presenter = new PostsPresenter(mockApiService, mockView);
    }

    @Test
    public void verifyGetPostDetails() {
        setSuccessfulGetPosts();
        TestSubscriber<List<PostModel>> subscriber = TestSubscriber.create();
        mockApiService.getPosts().subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertCompleted();

        List<PostModel> testModelList = subscriber.getOnNextEvents().get(0);
        PostModel postModel = (testModelList.get(0));
        String expectedBody = "some body";
        String expectedTitle = "some title";
        int expectedID = 123;

        assert(expectedBody.equalsIgnoreCase(postModel.getBody()));
        assert(expectedID == postModel.getId());
        assert(expectedTitle.equalsIgnoreCase(postModel.getTitle()));

        presenter.getPosts();

        Mockito.verify(mockView).setupAdapter(any());
    }

    @Test
    public void verifyError() {
        setUnsuccessfulGetPosts();

        TestSubscriber<List<PostModel>> subscriber = TestSubscriber.create();
        mockApiService.getPosts().subscribe(subscriber);
        subscriber.assertError(Throwable.class);

        presenter.getPosts();
        Mockito.verify(mockView).setError();
    }

    private void setUnsuccessfulGetPosts() {
        Throwable e = new Throwable();
        when(mockApiService.getPosts()).thenReturn(Observable.error(e));
    }

    private void setSuccessfulGetPosts() {
        List<PostModel> testModelList = new ArrayList<>();

        PostModel model1 = new PostModel();
        model1.setBody("some body");
        model1.setTitle("some title");
        model1.setId(123);
        testModelList.add(model1);

        when(mockApiService.getPosts()).thenReturn(Observable.just(testModelList));
    }

}