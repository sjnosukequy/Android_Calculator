package com.example.fullcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.fullcalculator.Fragments.BMI;
import com.example.fullcalculator.Fragments.Calc;
import com.example.fullcalculator.Fragments.Money;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    private  static  final  int Fragment_Calc = 0;
    private  static  final  int Fragment_BMI = 1;
    private  static  final  int Fragment_Money = 2;
    private int Current = Fragment_Calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.Drawer_Layout);
        toolbar = findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_Drawer_open, R.string.nav_Drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.Navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        ReplaceFrag(new Calc());
        navigationView.getMenu().findItem(R.id.Butt_Cal).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.Butt_Cal){
            if(Current !=  Fragment_Calc)
            {
                ReplaceFrag(new Calc());
                Current = Fragment_Calc;
            }
        } else if (id == R.id.Butt_BMI) {
            if(Current !=  Fragment_BMI)
            {
                ReplaceFrag(new BMI());
                Current = Fragment_BMI;
            }
        } else if (id == R.id.Butt_Money) {
            if(Current !=  Fragment_Money)
            {
                ReplaceFrag(new Money());
                Current = Fragment_Money;
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
            super .onBackPressed();
        }

        private void ReplaceFrag(Fragment fragment){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.Content_Frame,  fragment);
            transaction.commit();
        }

    }