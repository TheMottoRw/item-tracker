package com.example.itemtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class superAdmin extends AppCompatActivity {

    public Bundle passdata;
//    public ApplicationContext context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_admin);


        BottomNavigationView bottomNavigator = findViewById(R.id.nav_view);
        bottomNavigator.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.navigation_dashboard:
                        selectedFragment = new MainActivity();
                        break;
                    case R.id.navAdmin:
                        selectedFragment = new admin_view(); // add admin fragment
                        break;
                    case R.id.document:
                        selectedFragment = new documentType_view();
                        break;

                }

                selectedFragment.setArguments(passdata); // pass data from activity to fragments

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ftransaction = fm.beginTransaction();
                ftransaction.replace(R.id.framelayout,selectedFragment).commit();
                return true;
            }
        });
        setDefaultFragment();
    }
    public void setDefaultFragment(){ // this sets default fragment
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ftransaction = fm.beginTransaction();
        Fragment defaultFrgment = new MainActivity();
        defaultFrgment.setArguments(passdata); // pass data from activity to fragments

        ftransaction.replace(R.id.framelayout,defaultFrgment).commit(); // sets default to first
    }

}