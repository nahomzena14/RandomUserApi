package com.example.randomuserapi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.randomuserapi.databinding.ActivityMainBinding;
import com.example.randomuserapi.model.User;
import com.example.randomuserapi.presenter.Contract;
import com.example.randomuserapi.presenter.UserPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Contract.View{

    private ActivityMainBinding binding;
    private UserAdapter userAdapter = new UserAdapter(new ArrayList<>());
    private Contract.Presenter presenter = new UserPresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recyclerView.setAdapter(userAdapter);


        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(binding.recyclerView);

        //search button
        binding.sendButton.setOnClickListener(v ->{
            String value = binding.searchEdittext.getText().toString().trim();
            if(value.isEmpty()){
                Toast.makeText(this,"CAN NOT BE EMPTY.",Toast.LENGTH_SHORT).show();
            }
            else{
                presenter.getResults(value);
            }
        });
    }

    @Override
    public void displayResults(List<User> results) {
        userAdapter.setUserItems(results);
    }

    @Override
    public void setStatus(UserPresenter.Status status) {
        switch (status){
            case ERROR:
                showToast("An error occurred!");
                binding.progressbar.setVisibility(View.GONE);
                break;
            case LOADING:
                binding.progressbar.setVisibility(View.VISIBLE);
                break;
            case COMPLETE:
                binding.progressbar.setVisibility(View.GONE);
                break;
        }
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}