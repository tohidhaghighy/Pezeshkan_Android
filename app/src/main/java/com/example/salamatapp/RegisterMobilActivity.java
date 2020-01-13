package com.example.salamatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.salamatapp.CommonLayer.PersianCalender;
import com.example.salamatapp.DomainLayer.ListCity;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.DomainLayer.User;
import com.example.salamatapp.ServiceLayer.CityApiService;
import com.example.salamatapp.ServiceLayer.DoctorApiService;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import fr.castorflex.android.smoothprogressbar.SmoothProgressDrawable;

public class RegisterMobilActivity extends AppCompatActivity {
    private SmoothProgressBar mProgressBar;
    private CityApiService _cityapiservice;
    private String[] city_name;
    private Integer[] cityid;
    private Integer type;
    private EditText txtname,txtnumber,txtpass,txtrepass;
    private UserSharePrefrenceManager usermanager;
    private DoctorApiService _doctorapi;
    private Integer positioncity=0;
    PersianCalender pc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_mobil);
        final Spinner spinstate = (Spinner) findViewById(R.id.spineercity);
        Bundle extras = getIntent().getExtras();
        pc=new PersianCalender();

        if(extras == null) {
            type=0;
        } else {
            type= extras.getInt("type");
        }
        txtname=(EditText)findViewById(R.id.txtnameregister);
        txtnumber=(EditText)findViewById(R.id.txtmobileregister);
        txtpass=(EditText)findViewById(R.id.txtpasswordregister);
        txtrepass=(EditText)findViewById(R.id.txtrepasswordregister);
        usermanager=new UserSharePrefrenceManager(this);
        mProgressBar=(SmoothProgressBar)findViewById(R.id.progressbarregister);
        mProgressBar.setIndeterminateDrawable(new SmoothProgressDrawable.Builder(RegisterMobilActivity.this)
                .color(R.color.colorAccent)
                .interpolator(new DecelerateInterpolator())
                .sectionsCount(8)
                .separatorLength(8)       //You should use Resources#getDimension
                .progressiveStartSpeed(2)
                .progressiveStopSpeed(3.4f)
                .reversed(true)
                .mirrorMode(true)
                .progressiveStart(true)
                .build());

        mProgressBar.progressiveStart();
        _cityapiservice=new CityApiService(this);

        _cityapiservice.getCitiesString(new CityApiService.OnCitiesStringReceived() {
            @Override
            public void onReceived(ListCity city) {

                city_name=new String[city.getCityname().length];
                cityid=new Integer[city.getCityname().length];
                city_name=city.getCityname();
                cityid=city.getCityid();

                ArrayAdapter aa = new ArrayAdapter(RegisterMobilActivity.this,android.R.layout.simple_spinner_item,city.getCityname());
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinstate.setAdapter(aa);

            }

        });


        //Setting the ArrayAdapter data on the Spinner

        spinstate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {
                Object item = parent.getItemAtPosition(pos);

                positioncity=pos;

            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        final SaveUserItem user=new SaveUserItem();
        _doctorapi=new DoctorApiService(this);

        mProgressBar.progressiveStop();
        Button btnregister=(Button)findViewById(R.id.btnregistermobile);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (txtpass.getText().length()>8){
                    if (txtpass.getText().toString().equals(txtrepass.getText().toString())){
                        if (txtname.getText().toString()!="" && txtnumber.getText().toString()!=""){
                            Toast.makeText(RegisterMobilActivity.this,"لطفا منتظر بمانید",Toast.LENGTH_LONG).show();
                            final String changeuserformat=pc.PersianToEnglish(txtnumber.getText().toString());
                            mProgressBar.progressiveStart();
                            _doctorapi.postuser(txtname.getText().toString(),changeuserformat, txtpass.getText().toString(), type, cityid[positioncity], new DoctorApiService.OnDoctorLoginReceived() {
                                @Override
                                public void onReceived(String checklogin) {
                                    if (checklogin.equals("true")){
                                        user.setName(txtname.getText().toString());
                                        user.setPassword(txtpass.getText().toString());
                                        user.setNumber(changeuserformat);
                                        user.setIs_Active(false);
                                        user.setType(type);
                                        user.setFirstTime(0);
                                        usermanager.saveUser(user);
                                        Intent i = new Intent(RegisterMobilActivity.this, SmsResiveActivity.class);
                                        i.putExtra("type",type);
                                        startActivity(i);
                                        finish();

                                    }
                                    else {
                                        Toast.makeText(RegisterMobilActivity.this, "مشکل در ارتباط با سرور"+ checklogin,
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(RegisterMobilActivity.this, "مقادیر نباید خالی باشد",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(RegisterMobilActivity.this, "پسورد ها با هم مطابقت ندارند",
                                Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(RegisterMobilActivity.this, "طول پسورد باید از ۸ بیشتر باشد",
                            Toast.LENGTH_LONG).show();
                }


                mProgressBar.progressiveStop();
            }
        });

    }
}
