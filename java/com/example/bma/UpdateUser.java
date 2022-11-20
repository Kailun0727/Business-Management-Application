package com.example.bma;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bma.databinding.ActivityUpdateProductBinding;
import com.example.bma.databinding.ActivityUpdateUserBinding;

public class UpdateUser extends AppCompatActivity {

    ActivityUpdateUserBinding mBinding;
    private DBHandler db;
    private boolean isSaved;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set content view for update User page
        mBinding = ActivityUpdateUserBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        db = new DBHandler(this);

        //get intent data from previous activity
        Intent i = getIntent();
        int id = i.getIntExtra("id",0);

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
                    isSaved = db.updateUser(String.valueOf(id), userFullName, userUsername, userPassword);

                    //after update the info, redirect admin to user list page
                    Intent i = new Intent(UpdateUser.this, UserListActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(UpdateUser.this,"Please enter something to continue!",Toast.LENGTH_SHORT).show();
                }


                //if admin only enter user full name
                if (!userFullName.isEmpty()){
                    //use user id to update info
                    isSaved = db.updateFullName(String.valueOf(id), userFullName);

                    //after update the info, redirect admin to user list page
                    Intent i = new Intent(UpdateUser.this, UserListActivity.class);
                    startActivity(i);
                }

                //if admin only enter user username
                if (!userUsername.isEmpty()){
                    //use user id to update info
                    isSaved = db.updateUserName(String.valueOf(id), userUsername);

                    //after update the info, redirect admin to user list page
                    Intent i = new Intent(UpdateUser.this, UserListActivity.class);
                    startActivity(i);
                }

                //if admin only enter user password
                if (!userPassword.isEmpty()){
                    //use user id to update info
                    isSaved = db.updateUserPassword(String.valueOf(id), userPassword);

                    //after update the info, redirect admin to user list page
                    Intent i = new Intent(UpdateUser.this, UserListActivity.class);
                    startActivity(i);
                }

                if (isSaved == true){
                    Toast.makeText(UpdateUser.this,"Update successfully!",Toast.LENGTH_SHORT).show();
                }

            }
        });

        //delete the product
        mBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Create the object of AlertDialog Builder class
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateUser.this);

                // Set the message show for the Alert time
                builder.setMessage("Are you sure you want to delete this user?");

                // Set Alert Title
                builder.setTitle("Warning!");

                // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                builder.setCancelable(false);

                // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // When the user click yes button then user will be deleted
                    boolean isDelete = db.deleteUser(String.valueOf(id));

                    if(isDelete == true){
                        Toast.makeText(UpdateUser.this,"Delete successfully!", Toast.LENGTH_SHORT).show();
                        //after delete the user, redirect admin to user list page
                        Intent i = new Intent(UpdateUser.this, UserListActivity.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(UpdateUser.this,"Delete unsuccessfully", Toast.LENGTH_SHORT).show();
                    }
                });

                // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // If admin click no then dialog box is canceled.
                    dialog.cancel();
                });

                // Create the Alert dialog
                builder.create();
                // Show the Alert Dialog box
                builder.show();



            }
        });
    }
}
