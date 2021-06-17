package com.example.watchtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.

 */
public class ProfileFragment extends Fragment {

    TextView mCreateBtn,mLoginBtn;
    View v;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_profile, container, false);
        mCreateBtn = v.findViewById(R.id.loginhere);
        mLoginBtn = v.findViewById(R.id.registerhere);


        mCreateBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mCreateBtn = (TextView) v.findViewById(R.id.loginhere);
                        Intent i = new Intent();
                        i.setClass(getActivity(), Login.class);
                        startActivity(i);

                    }
                });


        mLoginBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mLoginBtn = (TextView) v.findViewById(R.id.registerhere);
                        Intent i = new Intent();
                        i.setClass(getActivity(), Register.class);
                        startActivity(i);

                    }
                });

        return v;
    }



    }





