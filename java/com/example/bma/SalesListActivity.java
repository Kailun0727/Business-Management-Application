package com.example.bma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class SalesListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new SalesListFragment();

    }
}