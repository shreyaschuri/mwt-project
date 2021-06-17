package com.example.watchtracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class MoviePage extends Fragment {

    View v;
    String title,vote,overview,image;
    Button addnow,addlater;
    public MoviePage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_movie_page, container, false);

        Bundle bundle = getArguments();

        TextView textTitle = v.findViewById(R.id.movie_title);
        TextView textOverview = v.findViewById(R.id.movie_overview);
        ImageView imageView = v.findViewById(R.id.movie_img);

        if (bundle != null) {
            title=bundle.getString("Title");
            vote=bundle.getString("VoteAverage");
            overview=bundle.getString("Overview");
            image=bundle.getString("ImgUrl");

            textTitle.setText(title);
            textOverview.setText(overview);

            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/original"+image)
                    .into(imageView);


        }

        addnow=v.findViewById(R.id.add_submit);
        addnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert();
            }
        });


        addlater=v.findViewById(R.id.add_teacher);
        addlater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsertteacher();
            }
        });


        return v;
    }

    private void processinsertteacher()
    {
        Map<String,Object> map=new HashMap<>();
        map.put("Title",title);
        map.put("Vote average",vote);
        map.put("Overview",overview);
        map.put("Image Url",image);
        FirebaseDatabase.getInstance().getReference().child("Watchlater").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(getContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {

                        Toast.makeText(getContext(),"Could not insert",Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void processinsert()
    {

        Map<String,Object> map=new HashMap<>();
        map.put("Title",title);
        map.put("Vote average",vote);
        map.put("Overview",overview);
        map.put("Image Url",image);

        FirebaseDatabase.getInstance().getReference().child("Watchnow").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(getContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {

                        Toast.makeText(getContext(),"Could not insert",Toast.LENGTH_LONG).show();
                    }
                });
    }



}