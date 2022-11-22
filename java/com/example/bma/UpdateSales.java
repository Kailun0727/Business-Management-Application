package com.example.bma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bma.databinding.ActivityUpdateSalesBinding;

public class UpdateSales extends AppCompatActivity {

    ActivityUpdateSalesBinding mUpdateSalesBinding;
    private DBHandler db;
    private boolean isSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DBHandler(this);

        mUpdateSalesBinding = ActivityUpdateSalesBinding.inflate(getLayoutInflater());
        setContentView(mUpdateSalesBinding.getRoot());

        //get intent data from previous activity
        Intent i = getIntent();
        int id = i.getIntExtra("id",0);

        mUpdateSalesBinding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String total = mUpdateSalesBinding.totalEditText.getText().toString();

                // get the values for day of month , month and year from a date picker
                String day = String.valueOf(mUpdateSalesBinding.simpleDatePicker.getDayOfMonth());
                String month = String.valueOf((mUpdateSalesBinding.simpleDatePicker.getMonth() + 1));
                String year = String.valueOf(mUpdateSalesBinding.simpleDatePicker.getYear());

                String date = year + "-" + month + "-" + day;

                //check the edit text and date picker is empty or not
                if (!total.isEmpty() && !date.isEmpty()){
                    isSaved = db.updateSales(String.valueOf(id),date, Integer.valueOf(total));

                    if (isSaved == true){
                        Toast.makeText(UpdateSales.this,"Sales updated successfully!",Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(UpdateSales.this,SalesListActivity.class);
                        startActivity(i);
                    }
                }

                if (!total.isEmpty()){
                    isSaved = db.updateSalesTotal(String.valueOf(id), Integer.valueOf(total));

                    if (isSaved == true){
                        Toast.makeText(UpdateSales.this,"Total profit updated successfully!",Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(UpdateSales.this,SalesListActivity.class);
                        startActivity(i);
                    }
                }

                if (!date.isEmpty()){
                    isSaved = db.updateSalesDate(String.valueOf(id), date);

                    if (isSaved == true){
                        Toast.makeText(UpdateSales.this,"Date updated successfully!",Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(UpdateSales.this,SalesListActivity.class);
                        startActivity(i);
                    }
                }


            }
        });
    }
}