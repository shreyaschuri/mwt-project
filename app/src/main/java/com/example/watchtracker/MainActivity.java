package com.example.watchtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView btnavViewId;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnavViewId=findViewById(R.id.bottom_navigation);
        frameLayout=findViewById(R.id.frameLayoutId);

        setFragment(new HomeFragment());

        btnavViewId.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.home:
                        btnavViewId.setItemBackgroundResource(R.color.colorAccent);
                        setFragment(new HomeFragment());
                        return true;

                    case R.id.watch:
                        btnavViewId.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(new WatchNowFragment());
                        return true;

                    case R.id.search:
                        btnavViewId.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(new SearchFragment());
                        return true;

                    case R.id.play:
                        btnavViewId.setItemBackgroundResource(R.color.colorAccent);
                        setFragment(new WatchLaterFragment());
                        return true;

                    case R.id.login:
                        btnavViewId.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(new ProfileFragment());
                        return true;

                    default:
                        return false;


                }

            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutId, fragment);
        fragmentTransaction.commit();
    }
}