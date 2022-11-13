package com.example.bma;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

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

        ProductListFragment productListFragment = new ProductListFragment();

        ProfileFragment profileFragment = new ProfileFragment();

        UserMenuFragment userMenuFragment = new UserMenuFragment();

        AdminMenuFragment adminMenuFragment = new AdminMenuFragment();

        mBottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Retrieving the value using its keys the file name
        // must be same in both saving and retrieving the data
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        // The value will be default as empty string because for
        // the very first time when the app is opened, there is nothing to show
        String login = sh.getString("login","");




        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,productListFragment).commit();

        mBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homepage:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,productListFragment).commit();
                        return true;

                    case R.id.menu:

                        if (login == "user"){
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,userMenuFragment).commit();
                            return true;

                        }
                        else if (login == "admin"){
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,adminMenuFragment).commit();
                            return true;
                        }


                    case R.id.person:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,profileFragment).commit();
                        return true;
                }
                return false;
            }
        });


        //Here working
//        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
//        if(fragment == null){
//            //create a fragment
//            fragment = createFragment();
//            fm.beginTransaction()
//                    .add(R.id.fragment_container, fragment)
//                    .commit();
//        }



    }

    public void clicked_add(View view) {
        Intent i = new Intent(this,AddProductPage.class);
        startActivity(i);
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
