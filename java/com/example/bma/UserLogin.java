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

        // set the TextChange Listener for both username & password
        mUserLoginBinding.usernameEditText.addTextChangedListener(textWatcher);
        mUserLoginBinding.passwordEditText.addTextChangedListener(textWatcher);

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

                Intent i = new Intent(UserLogin.this, ProductListActivity.class);
                startActivity(i);
            }
        });

    }

    //create text watcher for username & password input
    public TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            String username = mUserLoginBinding.usernameEditText.getText().toString();
            String password = mUserLoginBinding.passwordEditText.getText().toString();

            mUserLoginBinding.loginButton.setEnabled(!username.isEmpty() && !password.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}