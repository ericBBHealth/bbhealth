package com.jsonapp.ericbbhealth.jsonapp;

import com.jsonapp.ericbbhealth.jsonapp.Api.Models.CommentsModel;
import com.jsonapp.ericbbhealth.jsonapp.Api.Models.DetailPostModel;
import com.jsonapp.ericbbhealth.jsonapp.Api.Models.PostModel;
import com.jsonapp.ericbbhealth.jsonapp.Api.Models.UsersModel;
import com.jsonapp.ericbbhealth.jsonapp.Api.Services.Implementation.ApiService;
import com.jsonapp.ericbbhealth.jsonapp.DetailFeature.PostDetailContract;
import com.jsonapp.ericbbhealth.jsonapp.DetailFeature.PostDetailPresenter;
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
 * Created by eric_ on 16/09/2017.
 */
@RunWith(JUnit4.class)
public class PostDetailPresenterTest {
    @ClassRule
    public static final RxImmdeiateSchedulerRule overrideRule = new RxImmdeiateSchedulerRule();

    @Mock
    ApiService mockApiService;

    @Mock
    PostDetailContract.View mockView;

    private PostDetailPresenter presenter;

    @Before
    public void setup() throws Exception {

        MockitoAnnotations.initMocks(this);

        presenter = new PostDetailPresenter(mockApiService, mockView);
    }

    @Test
    public void getUsers() {
        setSuccessfulGetUsers();

        TestSubscriber<List<UsersModel>> subscriber = TestSubscriber.create();
        mockApiService.getUsers().subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertCompleted();


        List<UsersModel> testModelList = subscriber.getOnNextEvents().get(0);
        UsersModel userModel = (testModelList.get(0));
        String expectedEmail = "test@email.com";
        String expectedUsername = "A username";
        int expectedID = 123;

        assert(expectedEmail.equalsIgnoreCase(userModel.getEmail()));
        assert(expectedID == userModel.getId());
        assert(expectedUsername.equalsIgnoreCase(userModel.getUsername()));

        mockApiService.getUsers();

    }
    @Test
    public void getComments() {
        setSuccessfulGetComments();

        TestSubscriber<List<CommentsModel>> subscriber = TestSubscriber.create();
        mockApiService.getCommentsByPostId("").subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertCompleted();


        List<CommentsModel> testModelList = subscriber.getOnNextEvents().get(0);
        CommentsModel userModel = (testModelList.get(0));
        String expectedEmail = "test@email.com";
        String expectedBody = "some body";
        int expectedID = 123;

        assert(expectedEmail.equalsIgnoreCase(userModel.getEmail()));
        assert(expectedID == userModel.getId());
        assert(expectedBody.equalsIgnoreCase(userModel.getBody()));

        mockApiService.getCommentsByPostId(any());

    }

    private void setSuccessfulGetUsers() {
        List<UsersModel> testModelList = new ArrayList<>();

        UsersModel model1 = new UsersModel();
        model1.setEmail("test@email.com");
        model1.setUsername("A username");
        model1.setId(123);
        testModelList.add(model1);

        when(mockApiService.getUsers()).thenReturn(Observable.just(testModelList));
    }

    private void setSuccessfulGetComments() {
        List<CommentsModel> testModelList = new ArrayList<>();

        CommentsModel model1 = new CommentsModel();
        model1.setBody("some body");
        model1.setEmail("some email");
        model1.setId(123);
        model1.setPostId(456);
        model1.setName("James Smith");
        testModelList.add(model1);

        when(mockApiService.getCommentsByPostId("")).thenReturn(Observable.just(testModelList));
    }

}
