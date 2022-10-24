package com.example.bma;

import androidx.fragment.app.Fragment;

public class ProductListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {

        return new ProductListFragment();

    }

}
