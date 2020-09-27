package com.example.watchmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TableLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private List<Slide> lstSlides ;
    private ViewPager sliderpager;
    private TabLayout indicator;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliderpager = findViewById(R.id.slider_pager)  ;
        indicator = findViewById(R.id.indicator);


        //prepare list of slides
        lstSlides = new ArrayList<>();
        lstSlides.add(new Slide(R.drawable.slide2, "Slide text /nmore text here"));
        lstSlides.add(new Slide(R.drawable.slide1, "Slide text /nmore text here"));
        lstSlides.add(new Slide(R.drawable.slide4, "Slide text /nmore text here"));
        lstSlides.add(new Slide(R.drawable.slide5, "Slide text /nmore text here"));

        SliderPagerAdapter adapter = new SliderPagerAdapter(this, lstSlides);

        sliderpager.setAdapter(adapter);
        //setup time
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MainActivity.SliderTimer(),4000,6000);

        indicator.setupWithViewPager(sliderpager,true);



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
                            startActivity(new Intent(getApplicationContext()
                                    ,Login.class ));
                            overridePendingTransition(0,0);
                            return true;
                }


                return false;
            }
        });
    }
    class SliderTimer extends TimerTask {
        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderpager.getCurrentItem()<lstSlides.size()-1) {
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem()+1);
                    }
                    else
                        sliderpager.setCurrentItem(0);
                }
            });

        }
    }



}
