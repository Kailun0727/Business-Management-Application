package com.example.bma;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bma.databinding.FragmentProductBinding;
import com.example.bma.databinding.FragmentUserListBinding;
import com.example.bma.databinding.ListItemProductBinding;
import com.example.bma.databinding.ListItemUserBinding;

import java.util.ArrayList;
import java.util.List;

public class UserListFragment extends Fragment {

    //create User object
    private FragmentUserListBinding userListBinding;
    private UserAdapter mAdapter;
    private List<User> mUserList;
    private DBHandler db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        userListBinding = FragmentUserListBinding.inflate(getLayoutInflater());
        return userListBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        userListBinding.userRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();

    }

    private void updateUI() {
        db = new DBHandler(getActivity());

        mUserList = new ArrayList<>();

        //Use cursor to store all product
        Cursor c = db.readAllProduct();

        if(c.getCount() == 0 ){
            Toast.makeText(getActivity(),"No data.",Toast.LENGTH_SHORT).show();
        }
        else{
            while (c.moveToNext()){
                User user = new User();

                //after create the product object, save it into array list
                user.setUsername(c.getString(1));
                user.setPassword(c.getString(2));
                mUserList.add(user);
            }
        }


        //provide product array list to adapter
        mAdapter = new UserListFragment.UserAdapter(mUserList);
        userListBinding.userRecyclerView.setAdapter(mAdapter);


    }

    //recycler view adapter
    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{

        private List<User> mUser;

        public UserAdapter(List<User> users){
            mUser = users;
        }

        @NonNull
        @Override
        public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ListItemUserBinding itemBinding= ListItemUserBinding.inflate(getLayoutInflater());

            return new UserHolder(itemBinding);

        }

        @Override
        public void onBindViewHolder(@NonNull UserHolder holder, int position) {

            User user = mUser.get(position);
            holder.bindUser(user);

        }


        //size of object
        @Override
        public int getItemCount() {
            return mUser.size();
        }
    }

    //recycler view viewholder
    private class UserHolder extends RecyclerView.ViewHolder{

        private User mUser;
        private TextView mUserFullName;
        private TextView mUserUsername;
        private TextView mUserPassword;
        private Button mBtnEdit;


        public void bindUser(User user){

            mUser = user;
            mUserFullName.setText(mUser.getFullName());
            mUserUsername.setText(mUser.getUsername());
            mUserPassword.setText(mUser.getPassword());

            mBtnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println(String.valueOf(mUser.getId()));

                    Intent i = new Intent(getActivity(), UpdateUser.class);
                    i.putExtra("id", mUser.getId());
                    startActivity(i);
                }
            });



        }

        public UserHolder(ListItemUserBinding itemBinding){
            super(itemBinding.getRoot());

            mUserFullName = itemBinding.listItemUserFullName;
            mUserUsername = itemBinding.listItemUserUsername;
            mUserPassword = itemBinding.listItemUserPassword;
            mBtnEdit = itemBinding.btnEdit;

        }
    }

}