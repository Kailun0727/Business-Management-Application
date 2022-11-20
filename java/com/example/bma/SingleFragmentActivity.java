package com.example.bma;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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
    private String login;

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
        login = sh.getString("login","");

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,productListFragment).commit();

        mBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

                // The value will be default as empty string because for
                // the very first time when the app is opened, there is nothing to show
                login = sh.getString("login","");

                switch (item.getItemId()){
                    case R.id.product:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,productListFragment).commit();
                        return true;

                    case R.id.sales:
                        String menu_code = login.toString();
                        if (menu_code.equalsIgnoreCase("user")){
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,userMenuFragment).commit();
                            return true;
                        }
                        else if (menu_code.equalsIgnoreCase("admin")){
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,adminMenuFragment).commit();
                            return true;
                        }else {
                            Log.d("MyApp2",login);
                            return true;
                        }

                    case R.id.user:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,profileFragment).commit();
                        return true;

                    case R.id.profile:
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


}
