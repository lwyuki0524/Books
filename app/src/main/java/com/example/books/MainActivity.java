package com.example.books;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.books.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import sqlite.MyDBHelper;

public class MainActivity extends AppCompatActivity {

    private static String DATABASE_TABLE = "accounting_TABLE";
    private SQLiteDatabase db;
    private MyDBHelper mydbHelper;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    AppBarConfiguration appBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_add, R.id.navigation_home, R.id.navigation_book)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


//        manager = getSupportFragmentManager();
//        if(manager.findFragmentById(R.id.nav_host_fragment)==null){
//            HomeFragment list = new HomeFragment();
//            transaction = manager.beginTransaction();
//            transaction.add(android.R.id.list,list);
//            transaction.commit();
//        }


    }

//    public void setFraText(){
//        FragmentManager fm = getSupportFragmentManager();
//
////if you added fragment via layout xml
//        HomeFragment fragment =  (HomeFragment) fm.findFragmentById(R.id.navigation_home);
//        fragment.setText();
//    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
//    }



}
