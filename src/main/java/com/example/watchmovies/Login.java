package com.example.watchmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Initialize and Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){


                    case  R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class ));
                        overridePendingTransition(0,0);
                        return true;

                    case  R.id.watch:
                        startActivity(new Intent(getApplicationContext()
                                ,WatchNow.class ));
                        overridePendingTransition(0,0);
                        return true;

                    case  R.id.search:
                        startActivity(new Intent(getApplicationContext()
                                ,Search.class ));
                        overridePendingTransition(0,0);
                        return true;


                    case  R.id.play:
                        startActivity(new Intent(getApplicationContext()
                                ,WatchLater.class ));
                        overridePendingTransition(0,0);
                        return true;


                    case  R.id.login:

                        return true;
                }


                return false;
            }
        });
    }
}