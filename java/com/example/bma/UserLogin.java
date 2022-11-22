package com.example.bma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.bma.databinding.ActivityUserLoginBinding;

public class UserLogin extends AppCompatActivity {

    private User mUser;
    private ActivityUserLoginBinding mUserLoginBinding;
    private DBHandler dbHandler;
    private Admin mAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create new User object
        mUser = new User();

        dbHandler = new DBHandler(this);

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


                //check the database
                String name = mUserLoginBinding.usernameEditText.getText().toString();
                String ps = mUserLoginBinding.passwordEditText.getText().toString();

                //Read user part
                Cursor c = dbHandler.readUser(name, ps);

                if (c.getCount() >= 1) {

                    while (c.moveToNext()){
                        // Storing data into SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

                        // Creating an Editor object to edit(write to the file)
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();

                        String id = String.valueOf(c.getInt(0));
                        // Storing the key and its value as the data fetched from edittext
                        myEdit.putString("login", "user");
                        myEdit.putString("id",id);
                        myEdit.putString("name",name);

                        // Once the changes have been made,
                        // we need to commit to apply those changes made,
                        // otherwise, it will throw an error
                        myEdit.commit();
                    }


                    Intent i = new Intent(UserLogin.this, ProductListActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(UserLogin.this, "The username or password is wrong, Please try again", Toast.LENGTH_SHORT).show();
                }


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