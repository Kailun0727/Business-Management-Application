package com.example.bma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.bma.databinding.ActivityUserLoginBinding;

public class UserLogin extends AppCompatActivity {

    private User mUser;
    private ActivityUserLoginBinding mUserLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create new User object
        mUser = new User();

        //set content view for user login page
        mUserLoginBinding = ActivityUserLoginBinding.inflate(getLayoutInflater());
        setContentView(mUserLoginBinding.getRoot());

        //create text watcher for username input
        mUserLoginBinding.usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                mUser.setUsername(s.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //create text watcher for password input
        mUserLoginBinding.passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                mUser.setPassword(s.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //redirect to admin login page
        mUserLoginBinding.redirectAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(UserLogin.this, AdminLogin.class);
                startActivity(i);

            }
        });

        //login to the app
        mUserLoginBinding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(UserLogin.this, Homepage.class);
                startActivity(i);
            }
        });

    }
}