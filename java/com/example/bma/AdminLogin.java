package com.example.bma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.bma.databinding.ActivityAdminLoginBinding;

public class AdminLogin extends AppCompatActivity {

    private Admin mAdmin;
    private ActivityAdminLoginBinding mAdminLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create new admin object
        mAdmin = new Admin();

        //set content view for admin login page
        mAdminLoginBinding =ActivityAdminLoginBinding.inflate(getLayoutInflater());
        setContentView(mAdminLoginBinding.getRoot());

        //create text watcher for admin username input
        mAdminLoginBinding.usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                mAdmin.setUsername(s.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //create text watcher for admin password input
        mAdminLoginBinding.passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                mAdmin.setPassword(s.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //redirect to user login page
        mAdminLoginBinding.redirectUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(AdminLogin.this, UserLogin.class);
                startActivity(i);

            }
        });

        //login to the app
        mAdminLoginBinding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(AdminLogin.this, Homepage.class);
                startActivity(i);

            }
        });
    }
}