package com.example.bma;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bma.databinding.FragmentProductBinding;
import com.example.bma.databinding.ListItemProductBinding;

import java.util.ArrayList;
import java.util.List;

public class ProductListFragment extends Fragment {

    //create product object
    //Product product = new Product();
    private FragmentProductBinding productBinding;
    private ProductAdapter mAdapter;
    private List<Product> mProductList;

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

        mProductList = new ArrayList<>();
        for(int i=0; i<10; i++){
            Product product = new Product();
            product.setProductName("Product #" + i);
            product.setProductQuantity(i);
            mProductList.add(product);
        }

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
        private EditText mUpdateProductQuantity;
        private Button mBtnEdit;


        public void bindProduct(Product product){

            mProduct = product;
            mProductName.setText(mProduct.getProductName());
            mProductQuantity.setText(String.valueOf(mProduct.getProductQuantity()));

        }

        public ProductHolder(ListItemProductBinding itemBinding){
            super(itemBinding.getRoot());

            mProductName = itemBinding.listItemProductName;
            mProductQuantity = itemBinding.listItemProductQuantity;
            mUpdateProductQuantity = itemBinding.listItemProductQuantityEt;
            mBtnEdit = itemBinding.btnEdit;


        }
    }
}