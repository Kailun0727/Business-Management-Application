package com.example.bma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bma.databinding.ActivityUpdateUserBinding;
import com.example.bma.databinding.ActivityUserEditProfileBinding;

public class UserEditProfile extends AppCompatActivity {

    ActivityUserEditProfileBinding mBinding;
    private DBHandler db;
    private boolean isSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set content view for update User page
        mBinding = ActivityUserEditProfileBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        db = new DBHandler(this);

        //get data from shared preference
        SharedPreferences prefs = this.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String id = prefs.getString("id", null);

        mBinding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get data from edit text
                String userFullName = mBinding.fullNameEditText.getText().toString();
                String userUsername =  mBinding.usernameEditText.getText().toString();
                String userPassword =  mBinding.passwordEditText.getText().toString();

                //Update user info
                if (!userFullName.isEmpty() && !userUsername.isEmpty() && !userPassword.isEmpty()){
                    //use user id to update info
                    isSaved = db.updateUser(id, userFullName, userUsername, userPassword);

                    //after update the info, redirect admin to user list page
                    Toast.makeText(UserEditProfile.this, "Profile successfully updated!", Toast.LENGTH_SHORT).show();
                }



                //if admin only enter user full name
                if (!userFullName.isEmpty()){
                    //use user id to update info
                    isSaved = db.updateFullName(String.valueOf(id), userFullName);

                    Toast.makeText(UserEditProfile.this, "Full name successfully updated!", Toast.LENGTH_SHORT).show();
                }

                //if admin only enter user username
                if (!userUsername.isEmpty()){
                    //use user id to update info
                    isSaved = db.updateUserName(String.valueOf(id), userUsername);

                    Toast.makeText(UserEditProfile.this, "Username successfully updated!", Toast.LENGTH_SHORT).show();
                }

                //if admin only enter user password
                if (!userPassword.isEmpty()){
                    //use user id to update info
                    isSaved = db.updateUserPassword(String.valueOf(id), userPassword);

                    Toast.makeText(UserEditProfile.this, "Password successfully updated!", Toast.LENGTH_SHORT).show();
                }

                if (isSaved == true){
                    Toast.makeText(UserEditProfile.this,"Update successfully!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UserEditProfile.this,"Please enter something to continue!",Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
}