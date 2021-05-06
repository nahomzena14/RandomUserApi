package com.example.randomuserapi.model.network;

import com.example.randomuserapi.model.UserList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class UserRetro {

    //https://randomuser.me/api/?results=25
    //BaseUrl = https://randomuser.me
    //Path = api/

    private UserService userService =  createRetrofit().create(UserService.class);

    private Retrofit createRetrofit(){
        return new Retrofit.Builder().
                baseUrl("https://randomuser.me")
                .addConverterFactory(GsonConverterFactory.create()).
                build();
    }

    public Call<UserList> searchQuery(String search){
        return userService.searchUser(search);
    }

    interface UserService{
        @GET("api")
        Call<UserList> searchUser(@Query("results")String search);
    }

}
