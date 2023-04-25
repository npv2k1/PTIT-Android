package com.example.nguyenapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.nguyenapp.adapter.AdapterViewpager;
import com.example.nguyenapp.animation.Animation;
import com.example.nguyenapp.database.ItemDB;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ItemDB itemDB;

    ViewPager viewPager;
    TabLayout tabLayout;

    BottomNavigationView bottomNavigationView;

    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // hide action bar
        getSupportActionBar().hide();

        itemDB = new ItemDB(this);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fab = findViewById(R.id.fab);

        AdapterViewpager adapterViewpager = new AdapterViewpager(getSupportFragmentManager(), 3);
        viewPager.setAdapter(adapterViewpager);

        viewPager.setPageTransformer(true, new Animation());

        viewPager.setAdapter(adapterViewpager);

        tabLayout.setupWithViewPager(viewPager);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mFood:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.mMovie:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.mTravel:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.mFood).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.mMovie).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.mTravel).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show add activity
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);


            }

        });
    }

    @Override
    public void onBackPressed() {

        if(viewPager.getCurrentItem() == 0)
            super.onBackPressed();
        else
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);

        super.onBackPressed();
    }
}