package com.example.salamatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.EditText;

import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

public class DetailDoctorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_doctor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        useritem=new SaveUserItem();
        usermanager=new UserSharePrefrenceManager(this);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id==R.id.help){
            SaveUserItem uu=new SaveUserItem();
            uu.setType(useritem.getType());
            uu.setIs_Active(true);
            uu.setNumber(useritem.getNumber());
            uu.setPassword(useritem.getPassword());
            uu.setName(useritem.getName());
            uu.setFirstTime(0);
            usermanager.saveUser(uu);
            Intent i = new Intent(DetailDoctorActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        }else if(id==R.id.nav_exit){
            SaveUserItem uu=new SaveUserItem();
            uu.setType(null);
            uu.setIs_Active(false);
            uu.setNumber("");
            uu.setPassword("");
            uu.setName("");
            uu.setFirstTime(0);
            usermanager.saveUser(uu);
            Intent i = new Intent(DetailDoctorActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }else if(id==R.id.nav_help){
            SaveUserItem uu=new SaveUserItem();
            uu.setType(useritem.getType());
            uu.setIs_Active(true);
            uu.setNumber(useritem.getNumber());
            uu.setPassword(useritem.getPassword());
            uu.setName(useritem.getName());
            uu.setFirstTime(0);
            usermanager.saveUser(uu);
            Intent i = new Intent(DetailDoctorActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
