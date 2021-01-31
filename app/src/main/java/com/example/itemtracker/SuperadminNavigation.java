package com.example.itemtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SuperadminNavigation extends AppCompatActivity {

    public Bundle passdata;
    private Helper helper;
//    public ApplicationContext context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_admin);
        helper = new Helper(SuperadminNavigation.this);


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
                        selectedFragment = new AdminView(); // add admin fragment
                        break;
                    case R.id.document:
                        selectedFragment = new DocumentTypeView();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        // return true so that the menu pop up is opened
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                helper.showToast("Successfull logged out");
                helper.logout();
                finish();
                startActivity(new Intent(SuperadminNavigation.this, Login.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}