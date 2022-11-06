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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bma.databinding.FragmentProductBinding;
import com.example.bma.databinding.ListItemProductBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductListFragment extends Fragment {

    //create product object
    //Product product = new Product();
    private FragmentProductBinding productBinding;
    private ProductAdapter mAdapter;
    private List<Product> mProductList;
    private DBHandler db;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        productBinding = FragmentProductBinding.inflate(getLayoutInflater());
        return productBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productBinding.productRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

    }


    private void updateUI() {
        db = new DBHandler(getActivity());

        mProductList = new ArrayList<>();


        //insert dummy data for testing

            Product testProduct = new Product();
            testProduct.setProductName("Product # 1");
            testProduct.setProductQuantity(1);
            testProduct.setProductPrice(1);
            boolean add = db.addProduct(testProduct);


        //Use cursor to store all product
        Cursor c = db.readAllProduct();

        if(c.getCount() == 0 ){
            Toast.makeText(getActivity(),"No data.",Toast.LENGTH_SHORT).show();
        }
        else{
            while (c.moveToNext()){
                Product product = new Product();

                //after create the product object, save it into array list
                product.setProductID(c.getInt(0));
                product.setProductName(c.getString(1));
                product.setProductQuantity(c.getInt(2));
                product.setProductPrice(c.getInt(3));
                mProductList.add(product);
            }
        }


        //provide product array list to adapter
        mAdapter = new ProductAdapter(mProductList);
        productBinding.productRecyclerView.setAdapter(mAdapter);



    }

    //recycler view adapter
    private class ProductAdapter extends RecyclerView.Adapter<ProductHolder>{

        private List<Product> mProduct;

        public ProductAdapter(List<Product> product){
            mProduct = product;
        }

        @NonNull
        @Override
        public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ListItemProductBinding itemBinding= ListItemProductBinding.inflate(getLayoutInflater());

            return new ProductHolder(itemBinding);

        }

        @Override
        public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

            Product product = mProduct.get(position);
            holder.bindProduct(product);


        }



        //size of object
        @Override
        public int getItemCount() {
            return mProduct.size();
        }
    }


    //recycler view viewholder
    private class ProductHolder extends RecyclerView.ViewHolder{

        private Product mProduct;
        private TextView mProductName;
        private TextView mProductQuantity;
        private TextView mProductPrice;
        private Button mBtnEdit;


        public void bindProduct(Product product){

            mProduct = product;
            mProductName.setText(mProduct.getProductName());
            mProductQuantity.setText(String.valueOf(mProduct.getProductQuantity()));
            mProductPrice.setText(String.valueOf(mProduct.getProductPrice()));

            mBtnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println(String.valueOf(mProduct.getProductID()));

                    Intent i = new Intent(getActivity(),UpdateProduct.class);
                    i.putExtra("id",mProduct.getProductID());
                    startActivity(i);
                }
            });



        }

        public ProductHolder(ListItemProductBinding itemBinding){
            super(itemBinding.getRoot());

            mProductName = itemBinding.listItemProductName;
            mProductQuantity = itemBinding.listItemProductQuantity;
            mProductPrice = itemBinding.listItemProductPrice;
            mBtnEdit = itemBinding.btnEdit;





        }
    }
}