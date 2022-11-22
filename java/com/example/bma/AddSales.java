package com.example.bma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.bma.databinding.ActivityAddSalesBinding;

public class AddSales extends AppCompatActivity {

    private DBHandler db;
    private ActivityAddSalesBinding mAddSalesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAddSalesBinding = ActivityAddSalesBinding.inflate(getLayoutInflater());
        setContentView(mAddSalesBinding.getRoot());

        db = new DBHandler(this);

        mAddSalesBinding.totalEditText.addTextChangedListener(textWatcher);

        mAddSalesBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String total = mAddSalesBinding.totalEditText.getText().toString();

                // get the values for day of month , month and year from a date picker
                String day = String.valueOf(mAddSalesBinding.simpleDatePicker.getDayOfMonth());
                String month = String.valueOf((mAddSalesBinding.simpleDatePicker.getMonth() + 1));
                String year = String.valueOf(mAddSalesBinding.simpleDatePicker.getYear());

                String date = year + "-" + month + "-" + day;

                //save the data
                boolean isSaved = db.addSales(date,Integer.valueOf(total));

                if (isSaved == true){
                    Toast.makeText(AddSales.this,"Sales added successfully!",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AddSales.this,"Please try again!",Toast.LENGTH_SHORT).show();
                }

                Intent i = new Intent(AddSales.this, SalesListActivity.class);
                startActivity(i);

            }
        });


    }

    public TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String total = mAddSalesBinding.totalEditText.getText().toString();

            //if all field is not empty , then set the button to enabled
            if (!total.isEmpty()){
                mAddSalesBinding.btnSave.setEnabled(true);
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}