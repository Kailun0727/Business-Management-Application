package com.example.bma;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

//        mBottomNavigationView = findViewById(R.id.bottomNavigationView);
//
//        mBottomNavigationView.setOnItemSelectedListener((NavigationBarView.OnItemSelectedListener) this);
//        mBottomNavigationView.setSelectedItemId(R.id.homepage);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if(fragment == null){
            //create a fragment
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.bottom_nav_menu, menu);
//        return true;
//    }
//
//    private NavigationBarView.OnItemSelectedListener mSelectedListener = new NavigationBarView.OnItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//            switch(item.getItemId()){
//
//                case R.id.homepage:
//
//                    FragmentManager fm = getSupportFragmentManager();
//                    Fragment fragment = fm.findFragmentById(R.id.fragment_container);
//                    if(fragment == null){
//                        //create a fragment
//                        fragment = createFragment();
//                        fm.beginTransaction()
//                                .add(R.id.fragment_container, fragment)
//                                .commit();
//                    }
////                    ProductListFragment fragment = new ProductListFragment();
////
////                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
////                    fragmentTransaction.replace(R.id.fragment_container, fragment);
////                    fragmentTransaction.commit();
//
//                    return true;
//
//
//
//            }
//
//
//            return false;
//        }
//    };
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
//
//        switch(item.getItemId()){
//
//            case R.id.homepage:
//                //FragmentManager fm = getSupportFragmentManager();
//                //Fragment fragment = fm.findFragmentById(R.id.fragment_container);
//                if(fragment == null){
//                    //create a fragment
//                    fragment = createFragment();
//                    fm.beginTransaction()
//                            .add(R.id.fragment_container, productListFragment)
//                            .commit();
//                }
//                return true;
//
//            case R.id.menu:
////                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, productListFragment);
//                return true;
//
//            case R.id.person:
////                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, productListFragment);
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//
//        }
//
//    }
}
