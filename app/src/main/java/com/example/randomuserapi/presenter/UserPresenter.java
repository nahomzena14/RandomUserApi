package com.example.randomuserapi.presenter;

import android.util.Log;

import com.example.randomuserapi.model.User;
import com.example.randomuserapi.model.UserList;
import com.example.randomuserapi.model.network.UserRetro;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPresenter implements Contract.Presenter {

    private Contract.View view;
    private UserRetro userRetro = new UserRetro();

    public UserPresenter(Contract.View view) {
        this.view = view;
    }


    @Override
    public void getResults(String query) {

        view.setStatus(Status.LOADING);
        userRetro.searchQuery(query).enqueue(new Callback<UserList>() {

            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {

                UserList userList;
                List<User> list;
                Log.d("TAG_X", "URL: "+call.request().url());

                if (response.body() != null)
                    userList = response.body();
                else return;
                if (userList.getResults() != null)
                    list = userList.getResults();
                else return;
                view.displayResults(list);
                view.setStatus(Status.COMPLETE);
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                Log.d("TAG_X", "REACHED ONFAILURE");
                view.setStatus(Status.ERROR);
            }
        });
    }

    public enum Status{
        LOADING,
        COMPLETE,
        ERROR
    }
}
