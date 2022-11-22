package com.example.bma;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bma.databinding.FragmentAdminProfileBinding;
import com.example.bma.databinding.FragmentProfileBinding;

public class AdminProfileFragment extends Fragment {

    private FragmentAdminProfileBinding mFragmentAdminProfileBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mFragmentAdminProfileBinding = FragmentAdminProfileBinding.inflate(getLayoutInflater());
        return mFragmentAdminProfileBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        //get data from shared preference
        SharedPreferences prefs = this.getActivity().getSharedPreferences("MySharedPref",  Context.MODE_PRIVATE);
        String name = prefs.getString("name", null);


        mFragmentAdminProfileBinding.name.setText(name);

        mFragmentAdminProfileBinding.addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), AddProductPage.class);
                startActivity(i);

            }
        });

        mFragmentAdminProfileBinding.addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), AddUserPage.class);
                startActivity(i);

            }
        });

        mFragmentAdminProfileBinding.addSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddSales.class);
                startActivity(i);
            }
        });

        mFragmentAdminProfileBinding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), UserLogin.class);
                startActivity(i);

            }
        });
    }
}
