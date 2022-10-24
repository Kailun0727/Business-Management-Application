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

        // set the TextChange Listener for both username & password
        mAdminLoginBinding.usernameEditText.addTextChangedListener(textWatcher);
        mAdminLoginBinding.passwordEditText.addTextChangedListener(textWatcher);

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

                Intent i = new Intent(AdminLogin.this, ProductListFragment.class);
                startActivity(i);

            }
        });

    }

    //create text watcher for admin username input
    public TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            String username = mAdminLoginBinding.usernameEditText.getText().toString();
            String password = mAdminLoginBinding.passwordEditText.getText().toString();

            mAdminLoginBinding.loginButton.setEnabled(!username.isEmpty() && !password.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}