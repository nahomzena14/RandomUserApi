package com.example.randomuserapi.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.randomuserapi.databinding.UserItemLayoutBinding;
import com.example.randomuserapi.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private List<User> userItems;

    public UserAdapter(List<User> userItems){
        this.userItems = userItems;
    }

    public void setUserItems(List<User> userItems){
        this.userItems = userItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        UserItemLayoutBinding binding = UserItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
        User user = userItems.get(position);

        Log.d("TAG_X", "MAIN: "+user.getName().getFirst()+user.getName().getLast());


        //name
        holder.binding.userNameTextView.setText("FULL NAME: "+user.getName().getFirst() + " " + user.getName().getLast());
        //dob
        holder.binding.dobTextView.setText("AGE: "+user.getDob().getAge());
        //gender
        holder.binding.genderTextView.setText("GENDER: "+user.getGender());
        //location
        holder.binding.locationTextView.setText("LOCATION: "+user.getLocation().getCity()+", "+user.getLocation().getState());
        //phone
        holder.binding.phoneTextView.setText("PHONE NUMBER: "+user.getPhone());
        //email
        holder.binding.emailTextView.setText("EMAIL: " +user.getEmail());
        //image*/
        Glide.with(holder.binding.getRoot())
                .applyDefaultRequestOptions(RequestOptions.centerCropTransform())
                .load(user.getPicture().getLarge())
                .into(holder.binding.userImageview);


    }

    @Override
    public int getItemCount() {
        return userItems.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        UserItemLayoutBinding binding;

        public UserViewHolder(@NonNull UserItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
