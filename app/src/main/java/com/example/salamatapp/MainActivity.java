package com.example.salamatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.Toast;

import com.example.salamatapp.AdaptorLayer.Doctoritem_Adaptor;
import com.example.salamatapp.AdaptorLayer.GroupItem_Adaptor;
import com.example.salamatapp.DoctorManagment.ManagmentDoctorActivity;
import com.example.salamatapp.DomainLayer.Group;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.DomainLayer.User;
import com.example.salamatapp.IllnessManagment.ManagmentIllnessActivity;
import com.example.salamatapp.JudgeManagment.ManagmentJudgeActivity;
import com.example.salamatapp.NurseManagment.ManagmentNurseActivity;
import com.example.salamatapp.PharmacyManagment.ManagmentPharmacyActivity;
import com.example.salamatapp.ServiceLayer.GroupApiService;
import com.example.salamatapp.ServiceLayer.UserApiService;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

import java.util.List;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Intent i ;
    private UserSharePrefrenceManager usermanager;
    EditText _searchedittext;
    private SaveUserItem useritem;
    private Integer type;
    private Integer StartHelp;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_city:
                    i= new Intent(MainActivity.this, ListDoctorActivity.class);
                    i.putExtra("type",3);
                    startActivity(i);
                    return true;
                case R.id.navigation_judge:
                    i = new Intent(MainActivity.this, ListDoctorActivity.class);
                    i.putExtra("type",2);
                    startActivity(i);
                    return true;
                case R.id.navigation_pharmacy:
                    i = new Intent(MainActivity.this, ListDoctorActivity.class);
                    i.putExtra("type",1);
                    startActivity(i);
                    return true;
                case R.id.navigation_dashboard:
                   Gotomanagment();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, " ورود به پنل مدیریت ",
                        Toast.LENGTH_LONG).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        useritem=new SaveUserItem();
        usermanager=new UserSharePrefrenceManager(this);
        useritem=usermanager.getUser();
        type=useritem.getType();
        StartHelp=useritem.getFirstTime();
        if (StartHelp==0){
            showdetail();
        }

        BottomNavigationView navView = findViewById(R.id.nav_view_list);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        final RecyclerView rv=(RecyclerView)findViewById(R.id.productrecycleben);
        GroupApiService _groupapi=new GroupApiService(this);
        final UserApiService _userapi=new UserApiService(this);
        _searchedittext=(EditText)findViewById(R.id.searchdoctortext);
        _searchedittext.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                _userapi.getDoctorsSearchText(_searchedittext.getText().toString(),new UserApiService.OnUsersReceived() {
                    @Override
                    public void onReceived(List<User> users) {

                        Doctoritem_Adaptor pa=new Doctoritem_Adaptor(MainActivity.this, users);
                        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                        rv.setAdapter(pa);
                    }
                });
            }
        });


        _groupapi.getDoctors(new GroupApiService.OnGroupsReceived() {
            @Override
            public void onReceived(List<Group> groups) {
                RecyclerView rv1=(RecyclerView)findViewById(R.id.grouprecycleben);
                GroupItem_Adaptor pa1=new GroupItem_Adaptor(MainActivity.this, groups);
                rv1.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                rv1.setAdapter(pa1);
            }
        });

        _userapi.getDoctors(new UserApiService.OnUsersReceived() {
            @Override
            public void onReceived(List<User> users) {

                Doctoritem_Adaptor pa=new Doctoritem_Adaptor(MainActivity.this, users);
                rv.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                rv.setAdapter(pa);
            }
        });




    }



    public  void Gotomanagment(){
        if (type==1){
            i = new Intent(MainActivity.this, ManagmentDoctorActivity.class);
        }else if (type==2){
            i = new Intent(MainActivity.this, ManagmentJudgeActivity.class);
        }else if(type==3){
            i = new Intent(MainActivity.this, ManagmentIllnessActivity.class);
        }else if(type==4){
            i = new Intent(MainActivity.this, ManagmentPharmacyActivity.class);
        }else if(type==5){
            i = new Intent(MainActivity.this, ManagmentNurseActivity.class);
        }
        startActivity(i);
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
            Intent i = new Intent(MainActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Gotomanagment();
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
            SaveUserItem uu=new SaveUserItem();
            uu.setType(useritem.getType());
            uu.setIs_Active(true);
            uu.setNumber(useritem.getNumber());
            uu.setPassword(useritem.getPassword());
            uu.setName(useritem.getName());
            uu.setFirstTime(0);
            usermanager.saveUser(uu);
            Intent i = new Intent(MainActivity.this, MainActivity.class);
            startActivity(i);
            finish();

        } else if (id == R.id.nav_home) {

        } else if (id == R.id.nav_login) {
            Gotomanagment();

        } else if (id == R.id.nav_share) {

        }else if(id==R.id.nav_exit){
            SaveUserItem uu=new SaveUserItem();
            uu.setType(6);
            uu.setIs_Active(false);
            uu.setNumber("");
            uu.setPassword("");
            uu.setName("");
            uu.setFirstTime(0);
            usermanager.saveUser(uu);
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showdetail()
    {
        SaveUserItem uu=new SaveUserItem();
        uu.setType(useritem.getType());
        uu.setIs_Active(true);
        uu.setNumber(useritem.getNumber());
        uu.setPassword(useritem.getPassword());
        uu.setName(useritem.getName());
        uu.setFirstTime(1);
        usermanager.saveUser(uu);

        new MaterialTapTargetPrompt.Builder(MainActivity.this)
                .setTarget(R.id.fab)
                .setBackgroundColour(getResources().getColor(R.color.colorPrimary))
                .setPrimaryText("ورود به پنل مدیریت")
                .setSecondaryText("در اینجا میتوانید اطلاعات شخصی خود را ببینید")
                .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener()
                {
                    @Override
                    public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state)
                    {
                        if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED)
                        {
                            show1();
                        }
                    }
                })
                .show();


    }

    public void show1(){
        new MaterialTapTargetPrompt.Builder(MainActivity.this)
                .setTarget(R.id.navigation_city)
                .setBackgroundColour(getResources().getColor(R.color.colorPrimary))
                .setPrimaryText("انتخاب شهر")
                .setSecondaryText("میتوانید شهر پیش فرض خود را تغییر دهید")
                .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener()
                {
                    @Override
                    public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state)
                    {
                        if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED)
                        {
                           show2();
                        }
                    }
                })
                .show();
    }

    public void show2(){
        new MaterialTapTargetPrompt.Builder(MainActivity.this)
                .setTarget(R.id.navigation_judge)
                .setBackgroundColour(getResources().getColor(R.color.colorPrimary))
                .setPrimaryText("کارشناس آزمایشگاه")
                .setSecondaryText("میتوانید عکس ازمایشگاه خود را ارسال کنید و جواب خود را بگیرید")
                .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener()
                {
                    @Override
                    public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state)
                    {
                        if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED)
                        {
                            show3();
                        }
                    }
                })
                .show();
    }
    public void show3(){
        new MaterialTapTargetPrompt.Builder(MainActivity.this)
                .setTarget(R.id.navigation_pharmacy)
                .setBackgroundColour(getResources().getColor(R.color.colorPrimary))
                .setPrimaryText("داروخانه")
                .setSecondaryText("نسخه داده شده توسط دکتر را میتوانید به دکتر ارسال کنید")
                .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener()
                {
                    @Override
                    public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state)
                    {
                        if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED)
                        {
                            // User has pressed the prompt target
                        }
                    }
                })
                .show();
    }
}
