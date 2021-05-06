package com.example.randomuserapi.presenter;

import com.example.randomuserapi.model.User;
import com.example.randomuserapi.model.UserList;

import java.util.List;

public interface Contract {

    interface Presenter{
        void getResults(String query);
    }

    interface View{
        void displayResults(List<User> results);
        void setStatus(UserPresenter.Status status);
    }
}
