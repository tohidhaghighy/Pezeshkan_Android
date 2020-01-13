package com.example.salamatapp.IllnessManagment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salamatapp.DomainLayer.Illness.Illness;
import com.example.salamatapp.DomainLayer.ListBime;
import com.example.salamatapp.DomainLayer.ListCity;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.BimeApiService;
import com.example.salamatapp.ServiceLayer.CityApiService;
import com.example.salamatapp.ServiceLayer.IllnessApiService;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

import de.hdodenhof.circleimageview.CircleImageView;
import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import fr.castorflex.android.smoothprogressbar.SmoothProgressDrawable;
import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class ManagmentIllnessInfoActivity extends AppCompatActivity {

    private TextView txtname,txtnumber;
    private EditText txtsuger,txtpressure,txtweight,txtage,txtserial,txtdate;
    private Button btndate,btnupdate;
    private Spinner spnbime,spncity;
    private SmoothProgressBar progressillnessinfo;
    private PersianCalendar initDate;
    private PersianDatePickerDialog picker;
    private CityApiService _cityapiservice;
    private BimeApiService _bimeapiservice;
    private IllnessApiService _illnessservice;
    private String[] city_name;
    private Integer[] cityid;
    private String[] bime_name;
    private Integer[] bimeid;
    private Integer positioncity,positionbime;
    private Integer illnessid;

    private Bitmap imgillnessBitmap;
    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;
    private CircleImageView circleimage;
    private ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment_illness_info);
        usermanager=new UserSharePrefrenceManager(this);
        useritem=new SaveUserItem();
        useritem=usermanager.getUser();
        Cast();

        txtname.setText(useritem.getName());
        txtnumber.setText(useritem.getNumber());

        btnback=(ImageView)findViewById(R.id.btnbacklogin);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void Cast(){
        circleimage=(CircleImageView)findViewById(R.id.imgillnessimageinfo);
        circleimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Imageselect();
            }
        });
        progressillnessinfo=(SmoothProgressBar)findViewById(R.id.progillnessinfo);
        progressillnessinfo.setIndeterminateDrawable(new SmoothProgressDrawable.Builder(ManagmentIllnessInfoActivity.this)
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

        progressillnessinfo.progressiveStart();
        initDate = new PersianCalendar();
        initDate.setPersianDate(1398, 5, 5);
        txtname=(TextView)findViewById(R.id.txtillnessnameinfo);
        txtnumber=(TextView)findViewById(R.id.txtillnessnumberinfo);
        txtsuger=(EditText)findViewById(R.id.txtillnesssugerinfo);
        txtpressure=(EditText)findViewById(R.id.txtillnesspressureinfo);
        txtweight=(EditText)findViewById(R.id.txtillnessweightinfo);
        txtage=(EditText)findViewById(R.id.txtillnessageinfo);
        txtserial=(EditText)findViewById(R.id.txtillnessserialinfo);
        txtdate=(EditText)findViewById(R.id.txtillnessdateinfo);
        btndate=(Button)findViewById(R.id.btnillnessadddateinfo);
        spnbime=(Spinner)findViewById(R.id.spnillnessbimeinfo);
        spncity=(Spinner)findViewById(R.id.spnillnesscityinfo);
        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker = new PersianDatePickerDialog(ManagmentIllnessInfoActivity.this)
                        .setPositiveButtonString("باشه")
                        .setNegativeButton("بیخیال")
                        .setTodayButton("امروز")
                        .setTodayButtonVisible(true)
                        .setInitDate(initDate)
                        .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                        .setMinYear(1300)
                        .setActionTextColor(Color.GRAY)
                        .setListener(new Listener() {
                            @Override
                            public void onDateSelected(PersianCalendar persianCalendar) {
                            txtdate.setText(persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay());
                            }

                            @Override
                            public void onDismissed() {

                            }
                        });

                picker.show();
            }
        });
        btnupdate=(Button)findViewById(R.id.btnillnessupdateinfo);
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                _illnessservice.postUpdateIllness(illnessid, useritem.getName(), Integer.parseInt(txtsuger.getText().toString()), Integer.parseInt(txtpressure.getText().toString()), cityid[positioncity], bimeid[positionbime], Integer.parseInt(txtweight.getText().toString()), Integer.parseInt(txtage.getText().toString()), txtserial.getText().toString(), txtdate.getText().toString(), imgillnessBitmap, new IllnessApiService.OnillnessimgReceived() {
                    @Override
                    public void onReceived(String doccheck) {
                        Toast.makeText(getApplicationContext()," با موفقیت ویرایش شد",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        _cityapiservice=new CityApiService(this);

        _bimeapiservice=new BimeApiService(this);

        _illnessservice=new IllnessApiService(this);
        _illnessservice.getIllness(useritem.getNumber().toString(), new IllnessApiService.OnillnessReceived() {
            @Override
            public void onReceived(Illness illnessinfo) {

                if (illnessinfo.getAge()!=null){
                    txtage.setText(illnessinfo.getAge()+"");
                }
                if (illnessinfo.getDate()!=null){
                    txtdate.setText(illnessinfo.getDate());
                }
                if (illnessinfo.getPressure()!=null){
                    txtpressure.setText(illnessinfo.getPressure()+"");
                }
                if (illnessinfo.getWeight()!=null){
                    txtweight.setText(illnessinfo.getWeight()+"");
                }
                if (illnessinfo.getSuger()!=null){
                    txtsuger.setText(illnessinfo.getSuger()+"");
                }
                txtserial.setText(illnessinfo.getInsuranceSerial());
                illnessid=illnessinfo.getId();
            }
        });
        _cityapiservice.getCitiesString(new CityApiService.OnCitiesStringReceived() {
            @Override
            public void onReceived(ListCity city) {

                city_name=new String[city.getCityname().length];
                cityid=new Integer[city.getCityname().length];
                city_name=city.getCityname();
                cityid=city.getCityid();

                ArrayAdapter aa = new ArrayAdapter(ManagmentIllnessInfoActivity.this,android.R.layout.simple_spinner_item,city.getCityname());
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spncity.setAdapter(aa);

            }

        });

        _bimeapiservice.getBimesString(new BimeApiService.OnBimesStringReceived() {
            @Override
            public void onReceived(ListBime bime) {
                bime_name=new String[bime.getBimename().length];
                bimeid=new Integer[bime.getBimename().length];
                bime_name=bime.getBimename();
                bimeid=bime.getBimeid();

                ArrayAdapter aa = new ArrayAdapter(ManagmentIllnessInfoActivity.this,android.R.layout.simple_spinner_item,bime.getBimename());
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnbime.setAdapter(aa);
            }
        });

        spncity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
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

        spnbime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {
                Object item = parent.getItemAtPosition(pos);

                positionbime=pos;

            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        progressillnessinfo.progressiveStop();
    }

    void Imageselect(){

        Intent i=new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,0);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        super.onActivityResult(resultCode,requestCode,data);
        if (resultCode==RESULT_OK && data!=null){

            Uri uri=data.getData();
            try{


                    imgillnessBitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    imgillnessBitmap=getResizedBitmap(imgillnessBitmap,400,400);
                    circleimage.setImageBitmap(imgillnessBitmap);

            }catch (Exception e){

                e.printStackTrace();
            }
        }
    }


    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

}
