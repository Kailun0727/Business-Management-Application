package com.example.bma;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bma.databinding.FragmentProductBinding;
import com.example.bma.databinding.FragmentSalesBinding;
import com.example.bma.databinding.ListItemProductBinding;
import com.example.bma.databinding.ListItemSalesBinding;

import java.util.ArrayList;
import java.util.List;

public class SalesListFragment extends Fragment {

    //create product object
    //Product product = new Product();
    private FragmentSalesBinding salesBinding;
    private SalesListFragment.SalesAdapter mAdapter;
    private List<Sales> mSalesList;
    private DBHandler db;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        salesBinding = FragmentSalesBinding.inflate(getLayoutInflater());
        return salesBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        salesBinding.salesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

    }


    private void updateUI() {
        db = new DBHandler(getActivity());

        mSalesList = new ArrayList<>();

        //Use cursor to store all product
        Cursor c = db.readAllSales();

        if(c.getCount() == 0 ){
            Toast.makeText(getActivity(),"No Sales data.",Toast.LENGTH_SHORT).show();
        }
        else{
            while (c.moveToNext()){
                Sales sales= new Sales();

                //after create the product object, save it into array list
                sales.setId(c.getInt(0));
                sales.setDate(c.getString(1));
                sales.setTotal(c.getInt(2));
                mSalesList.add(sales);
            }
        }


        //provide product array list to adapter
        mAdapter = new SalesListFragment.SalesAdapter(mSalesList);
        salesBinding.salesRecyclerView.setAdapter(mAdapter);



    }

    //recycler view adapter
    private class SalesAdapter extends RecyclerView.Adapter<SalesListFragment.SalesHolder>{

        private List<Sales> mSales;

        public SalesAdapter(List<Sales> sales){
            mSales = sales;
        }

        @NonNull
        @Override
        public SalesListFragment.SalesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ListItemSalesBinding itemBinding = ListItemSalesBinding.inflate(getLayoutInflater());

            return new SalesListFragment.SalesHolder(itemBinding);

        }

        @Override
        public void onBindViewHolder(@NonNull SalesListFragment.SalesHolder holder, int position) {

            Sales sales = mSales.get(position);
            holder.bindSales(sales);


        }

        //size of object
        @Override
        public int getItemCount() {
            return mSales.size();
        }
    }


    //recycler view viewholder
    private class SalesHolder extends RecyclerView.ViewHolder{

        private Sales mSales;
        private TextView mSalesDate;
        private TextView mSalesTotal;
        private Button mBtnEdit;


        public void bindSales(Sales sales){

            mSales = sales;
            mSalesDate.setText(mSales.getDate().toString());
            mSalesTotal.setText("Total Sales : RM"+String.valueOf(mSales.getTotal()));

            mBtnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getActivity(),UpdateSales.class);
                    i.putExtra("id",mSales.getId());
                    startActivity(i);
                }
            });



        }

        public SalesHolder(ListItemSalesBinding itemBinding){
            super(itemBinding.getRoot());
            mSalesDate = itemBinding.listItemSalesDate;
            mSalesTotal = itemBinding.listItemSalesTotal;
            mBtnEdit = itemBinding.btnEdit;

        }
    }
}