package com.example.bma;

import androidx.fragment.app.Fragment;

public class UserMenuActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new UserMenuFragment();

    }
}
