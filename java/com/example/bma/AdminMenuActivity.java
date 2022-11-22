package com.example.bma;

import androidx.fragment.app.Fragment;

public class AdminMenuActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new AdminMenuFragment();

    }

}
