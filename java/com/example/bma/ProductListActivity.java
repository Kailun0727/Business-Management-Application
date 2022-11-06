package com.example.bma;

import androidx.fragment.app.Fragment;

public class ProductListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new ProductListFragment();

    }

//    private void addProduct(){
//        db = new DBHandler(this);
//        for(int i=0; i<10; i++){
//            Product product = new Product();
//            product.setProductName("Product #" + i);
//            product.setProductQuantity(i);
//            product.setProductPrice(i);
//            db.addProduct(product.getProductID().toString(),product.getProductName(),product.getProductQuantity(),product.getProductPrice());
//
//        }
//    }

}
