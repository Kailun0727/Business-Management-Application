package com.example.bma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.bma.databinding.ActivityAddProductPageBinding;
import com.example.bma.databinding.ActivityUpdateProductBinding;

public class AddProductPage extends AppCompatActivity {

    private ActivityAddProductPageBinding mBinding;
    private DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityAddProductPageBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        db = new DBHandler(this);

//        setContentView(R.layout.activity_add_product_page);

        //add text watcher to edit text
        mBinding.nameEditText.addTextChangedListener(textWatcher);
        mBinding.quantityEditText.addTextChangedListener(textWatcher);
        mBinding.priceEditText.addTextChangedListener(textWatcher);

        mBinding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get input from edit text
                String name = mBinding.nameEditText.getText().toString();
                String quantity = mBinding.quantityEditText.getText().toString();
                String price = mBinding.priceEditText.getText().toString();

                //create a new product and set the data
                Product product = new Product();
                product.setProductName(name);
                product.setProductQuantity(Integer.valueOf(quantity));
                product.setProductPrice(Integer.valueOf(price));

                //add the data into database
                boolean addProduct = db.addProduct(product);

                if(addProduct == true){
                    Toast.makeText(AddProductPage.this,"Product has been added!",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AddProductPage.this,ProductListActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(AddProductPage.this,"Please try again!",Toast.LENGTH_SHORT).show();
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
            String name = mBinding.nameEditText.getText().toString();
            String quantity = mBinding.quantityEditText.getText().toString();
            String price = mBinding.priceEditText.getText().toString();

            //if all field is not empty , then set the button to enabled
            if (!name.isEmpty() && !quantity.isEmpty() && !price.isEmpty()){
                mBinding.btnUpdate.setEnabled(true);
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}