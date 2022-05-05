package com.example.yalla;

import android.os.Bundle;

import com.example.yalla.main2.SectionsPagerAdapter1;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.yalla.ui.main.SectionsPagerAdapter;
import com.example.yalla.databinding.ActivityAdminMenuBinding;

public class AdminMenu extends AppCompatActivity {

    private ActivityAdminMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityAdminMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter1 SectionsPagerAdapter1 = new SectionsPagerAdapter1(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager1;
        viewPager.setAdapter(SectionsPagerAdapter1);
        TabLayout tabs = binding.tabs1;
        tabs.setupWithViewPager(viewPager);
    }
}