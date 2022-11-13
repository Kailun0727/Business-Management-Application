package com.example.bma;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bma.databinding.ActivityUpdateProductBinding;

public class UpdateProduct extends AppCompatActivity {

    ActivityUpdateProductBinding mBinding;
    private DBHandler db;
    private boolean isSaved;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set content view for update page
        mBinding = ActivityUpdateProductBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        db = new DBHandler(this);


        //get intent data from previous activity
        Intent i = getIntent();
        int id = i.getIntExtra("id",0);


        mBinding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get data from edit text
                String productName = mBinding.nameEditText.getText().toString();
                String productQuantity =  mBinding.quantityEditText.getText().toString();
                String productPrice =  mBinding.priceEditText.getText().toString();

                //Update product info
                if (!productName.isEmpty() && !productQuantity.isEmpty() && !productPrice.isEmpty()){
                    //use product id to update info
                    isSaved = db.updateProduct(String.valueOf(id),productName,productQuantity,productPrice);

                    //after update the info, redirect user to product list page
                    Intent i = new Intent(UpdateProduct.this,ProductListActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(UpdateProduct.this,"Please enter something to continue!",Toast.LENGTH_SHORT).show();
                }


                //if user only enter productName
                if (!productName.isEmpty()){
                    //use product id to update info
                    isSaved = db.updateProductName(String.valueOf(id),productName);

                    //after update the info, redirect user to product list page
                    Intent i = new Intent(UpdateProduct.this,ProductListActivity.class);
                    startActivity(i);
                }

                //if user only enter productQuantity
                if (!productQuantity.isEmpty()){
                    //use product id to update info
                    isSaved = db.updateProductQuantity(String.valueOf(id),Integer.valueOf(productQuantity));

                    //after update the info, redirect user to product list page
                    Intent i = new Intent(UpdateProduct.this,ProductListActivity.class);
                    startActivity(i);
                }

                //if user only enter productPrice
                if (!productPrice.isEmpty()){
                    //use product id to update info
                    isSaved = db.updateProductPrice(String.valueOf(id),Integer.valueOf(productPrice));

                    //after update the info, redirect user to product list page
                    Intent i = new Intent(UpdateProduct.this,ProductListActivity.class);
                    startActivity(i);
                }

                if (isSaved == true){
                    Toast.makeText(UpdateProduct.this,"Update successfully!",Toast.LENGTH_SHORT).show();
                }

            }
        });

        //delete the product
        mBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Create the object of AlertDialog Builder class
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateProduct.this);

                // Set the message show for the Alert time
                builder.setMessage("Are you sure you want to delete this product?");

                // Set Alert Title
                builder.setTitle("Warning!");

                // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                builder.setCancelable(false);

                // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // When the user click yes button then product will be deleted
                    boolean isDelete = db.deleteProduct(String.valueOf(id));

                    if(isDelete == true){
                        Toast.makeText(UpdateProduct.this,"Delete successfully!",Toast.LENGTH_SHORT).show();
                        //after delete the product, redirect user to product list page
                        Intent i = new Intent(UpdateProduct.this,ProductListActivity.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(UpdateProduct.this,"Delete unsuccessfully",Toast.LENGTH_SHORT).show();
                    }
                });

                // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // If user click no then dialog box is canceled.
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