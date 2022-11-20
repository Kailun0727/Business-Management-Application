package com.example.bma;

import androidx.fragment.app.Fragment;

public class UserListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {

        return new UserListFragment();
    }

}
