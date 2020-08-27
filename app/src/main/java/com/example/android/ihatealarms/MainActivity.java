package com.example.android.ihatealarms;

import android.os.Bundle;
import android.view.ViewParent;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    private SectionsPageAdapter sectionsPageAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        viewPager= findViewById(R.id.view_pager);
        setupviewpager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    public void setupviewpager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new alarmactivity(),"Alarm");
        adapter.addFragment(new stopwatchactivity(),"StopWatch");
        adapter.addFragment(new timeractivity(),"Timer");
        viewPager.setAdapter(adapter);
    }
}