package com.example.bma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.bma.databinding.ActivityAddUserPageBinding;

public class AddUserPage extends AppCompatActivity {

    private DBHandler db;
    private ActivityAddUserPageBinding mAddUserPageBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_user_page);
        mAddUserPageBinding = ActivityAddUserPageBinding.inflate(getLayoutInflater());
        setContentView(mAddUserPageBinding.getRoot());

        //initialize database
        db = new DBHandler(this);

        //add text watcher to edit text
        mAddUserPageBinding.fullNameEditText.addTextChangedListener(textWatcher);
        mAddUserPageBinding.nameEditText.addTextChangedListener(textWatcher);
        mAddUserPageBinding.passwordEditText.addTextChangedListener(textWatcher);

        mAddUserPageBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get input from edit text
                String fullName = mAddUserPageBinding.fullNameEditText.getText().toString();
                String name = mAddUserPageBinding.nameEditText.getText().toString();
                String ps= mAddUserPageBinding.passwordEditText.getText().toString();

                //add the user into database
                boolean addUser = db.addUser(fullName,name,ps);

                if(addUser == true){
                    Toast.makeText(AddUserPage.this,"User has been added!",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AddUserPage.this, UserListActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(AddUserPage.this,"Please try again!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String fullName = mAddUserPageBinding.fullNameEditText.getText().toString();
            String name = mAddUserPageBinding.nameEditText.getText().toString();
            String ps= mAddUserPageBinding.passwordEditText.getText().toString();
            //if all field is not empty , then set the button to enabled
            if (!fullName.isEmpty() && !name.isEmpty() && !ps.isEmpty()){
                mAddUserPageBinding.btnSave.setEnabled(true);
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}