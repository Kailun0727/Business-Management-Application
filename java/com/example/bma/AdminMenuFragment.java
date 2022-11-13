package com.example.bma;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bma.databinding.FragmentAdminFragmentBinding;
import com.example.bma.databinding.FragmentUserMenuBinding;

public class AdminMenuFragment extends Fragment {

    private FragmentAdminFragmentBinding adminFragmentBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        adminFragmentBinding = FragmentAdminFragmentBinding.inflate(getLayoutInflater());
        return adminFragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}