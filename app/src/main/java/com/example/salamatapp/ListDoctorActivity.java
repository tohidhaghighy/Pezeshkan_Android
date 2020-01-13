package com.example.salamatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

import com.example.salamatapp.AdaptorLayer.Doctoritem_Adaptor;
import com.example.salamatapp.AdaptorLayer.PharmacyJudgeAdaptor;
import com.example.salamatapp.AdaptorLayer.TimeVisitDoctor_Adaptor;
import com.example.salamatapp.DoctorManagment.ManagmentDoctorDragActivity;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.DomainLayer.User;
import com.example.salamatapp.FragmentLayer.CityFragment;
import com.example.salamatapp.FragmentLayer.DoctorDetailFragment;
import com.example.salamatapp.ServiceLayer.DataFakeGenarator;
import com.example.salamatapp.ServiceLayer.NoskheApiService;
import com.example.salamatapp.ServiceLayer.ReserveApiService;
import com.example.salamatapp.ServiceLayer.UserApiService;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

import java.util.List;

public class ListDoctorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button btnrezervejudge;
    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;
    private ReserveApiService _rezerveapi;
    private Integer costjudge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_doctor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        usermanager=new UserSharePrefrenceManager(this);
        useritem=new SaveUserItem();
        useritem=usermanager.getUser();

        _rezerveapi=new ReserveApiService(this);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        final RecyclerView rv=(RecyclerView)findViewById(R.id.doctorrecycleben);

        btnrezervejudge=(Button)findViewById(R.id.btnrezervejudgetime);
        btnrezervejudge.setVisibility(View.INVISIBLE);
        int typeid;
        String groupid;
        Integer groupiditem;
        Integer cityid;
        int group;
        Bundle extras = getIntent().getExtras();

        if(extras == null) {
            typeid= 0;
            groupid="";
            groupiditem=0;
            group=0;
            cityid=0;
        } else {
            typeid= extras.getInt("type");
            groupid= extras.getString("group");
            group=extras.getInt("groupdoctor");
            groupiditem=extras.getInt("groupiditem");
            cityid=extras.getInt("cityid");
        }
        UserApiService _userapi=new UserApiService(this);

        //doctor
        if (typeid==0){

        }//pharmacy
        else if ( typeid==1){
            btnrezervejudge.setVisibility(View.VISIBLE);
            btnrezervejudge.setText("شما میتوانید با ارسال نسخه به داروخانه ها دارو های خود را تهیه کنید");
            _userapi.getPharmacys(new UserApiService.OnUsersReceived() {
                @Override
                public void onReceived(List<User> users) {
                    PharmacyJudgeAdaptor pa=new PharmacyJudgeAdaptor(ListDoctorActivity.this, users);
                    rv.setLayoutManager(new LinearLayoutManager(ListDoctorActivity.this,LinearLayoutManager.VERTICAL,false));
                    rv.setAdapter(pa);
                }
            });

        }//judge
        else if(typeid==2){
            _rezerveapi.getjudgecost(new NoskheApiService.OnReturnvalueReceived() {
                @Override
                public void onReceived(String checkvalue) {

                    btnrezervejudge.setText("رزرو وقت کارشناس آزمایشگاه با پرداخت : " + checkvalue + " ریال ");
                }
            });
            btnrezervejudge.setVisibility(View.VISIBLE);
            btnrezervejudge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                      if (useritem.getType()==3){
                          _rezerveapi.postaddreservejudge(useritem.getNumber().toString(), new NoskheApiService.OnReturnvalueReceived() {
                              @Override
                              public void onReceived(String checkvalue) {
                                  Toast.makeText(ListDoctorActivity.this, "با موفقیت رزرو شد ",
                                          Toast.LENGTH_LONG).show();
                              }
                          });
                      }else{
                         Toast.makeText(ListDoctorActivity.this, "فقط بیمار میتواند وقت رزرو کند !!",
                         Toast.LENGTH_LONG).show();
                      }
                }
            });
            _userapi.getJudges(new UserApiService.OnUsersReceived() {
                @Override
                public void onReceived(List<User> users) {
                    PharmacyJudgeAdaptor pa=new PharmacyJudgeAdaptor(ListDoctorActivity.this, users);
                    rv.setLayoutManager(new LinearLayoutManager(ListDoctorActivity.this,LinearLayoutManager.VERTICAL,false));
                    rv.setAdapter(pa);
                }
            });

        }
        else if(typeid==3){
            FragmentManager fragmentmanager=getSupportFragmentManager();
            FragmentTransaction transaction=fragmentmanager.beginTransaction();
            transaction.replace(R.id.framlayoutmanager,new CityFragment());
            transaction.commit();

        }
        else if (typeid==4){
            FragmentManager fragmentmanager=getSupportFragmentManager();
            FragmentTransaction transaction=fragmentmanager.beginTransaction();
            transaction.replace(R.id.framlayoutmanager,new DoctorDetailFragment());
            transaction.commit();
        }
        else if (typeid==9){
            _userapi.getDoctorsWithGroup(group,new UserApiService.OnUsersReceived() {
                @Override
                public void onReceived(List<User> users) {
                    Doctoritem_Adaptor pa=new Doctoritem_Adaptor(ListDoctorActivity.this, users);
                    rv.setLayoutManager(new LinearLayoutManager(ListDoctorActivity.this,LinearLayoutManager.VERTICAL,false));
                    rv.setAdapter(pa);
                }
            });
        }
        else if(typeid==8){
            _userapi.getDoctorsWithCity(group,new UserApiService.OnUsersReceived() {
                @Override
                public void onReceived(List<User> users) {
                    Doctoritem_Adaptor pa=new Doctoritem_Adaptor(ListDoctorActivity.this, users);
                    rv.setLayoutManager(new LinearLayoutManager(ListDoctorActivity.this,LinearLayoutManager.VERTICAL,false));
                    rv.setAdapter(pa);
                }
            });
        }else if(typeid==7){
            _userapi.getGroupCityDoctors(groupiditem,cityid,new UserApiService.OnUsersReceived() {
                @Override
                public void onReceived(List<User> users) {
                    Doctoritem_Adaptor pa=new Doctoritem_Adaptor(ListDoctorActivity.this, users);
                    rv.setLayoutManager(new LinearLayoutManager(ListDoctorActivity.this,LinearLayoutManager.VERTICAL,false));
                    rv.setAdapter(pa);
                }
            });
        }
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
        } else if (id == R.id.nav_help) {
            Intent i = new Intent(ListDoctorActivity.this, MainActivity.class);

            startActivity(i);
            finish();


        } else if (id == R.id.nav_home) {

        } else if (id == R.id.nav_login) {
            Intent i = new Intent(ListDoctorActivity.this, LoginActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
