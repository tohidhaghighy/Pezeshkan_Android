package com.example.salamatapp.DoctorManagment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salamatapp.DomainLayer.Doctor.DoctorInfo;
import com.example.salamatapp.DomainLayer.ListCity;
import com.example.salamatapp.DomainLayer.ListGroup;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.IllnessManagment.ManagmentIllnessInfoActivity;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.CityApiService;
import com.example.salamatapp.ServiceLayer.Converters.Doctor;
import com.example.salamatapp.ServiceLayer.DoctorApiService;
import com.example.salamatapp.ServiceLayer.GroupApiService;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

import de.hdodenhof.circleimageview.CircleImageView;

public class ManagmentDoctorInfoActivity extends AppCompatActivity {

    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;
    private TextView txtname,txtnumber;
    private EditText txtcost,txtdescription,txtnezamcode;
    private Button btndoctorupdateinfo;
    private Spinner spngroup,spncity;
    private CityApiService _cityapiservice;
    private DoctorApiService _doctorapiservice;
    private String[] city_name;
    private Integer[] cityid;
    private GroupApiService _groupapiservice;
    private String[] group_name;
    private Integer[] groupid;
    private Integer cityposition;
    private Integer groupposition;
    private Doctor _dcconverter;
    private Integer doctorid;
    private Bitmap imgdoctorBitmap;
    private CircleImageView circleimage;
    private ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment_doctor_info);

        usermanager=new UserSharePrefrenceManager(this);
        useritem=new SaveUserItem();
        useritem=usermanager.getUser();

        txtname=(TextView)findViewById(R.id.txtdoctornameinfo);
        txtnumber=(TextView)findViewById(R.id.txtdoctornumberinfo);
        txtnezamcode=(EditText)findViewById(R.id.txtdoctornezamcodeinfo);
        txtcost=(EditText)findViewById(R.id.txtdoctorcostinfo);
        txtdescription=(EditText)findViewById(R.id.txtdoctordescriptioninfo);
        spncity=(Spinner)findViewById(R.id.spndoctorcityinfo);
        spngroup=(Spinner)findViewById(R.id.spndoctorgroupinfo);
        btndoctorupdateinfo=(Button)findViewById(R.id.btndoctorupdateinfo);
        btnback=(ImageView)findViewById(R.id.btnbacklogin);
        circleimage=(CircleImageView)findViewById(R.id.imgdoctorimageinfo);
        circleimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Imageselect();
            }
        });

        txtname.setText(useritem.getName()+"");
        txtnumber.setText(useritem.getNumber()+"");
        _cityapiservice=new CityApiService(this);
        _doctorapiservice=new DoctorApiService(this);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        _doctorapiservice.getDoctorWithDoctor(useritem.getNumber().toString(), new DoctorApiService.OnDoctorItemReceived() {
            @Override
            public void onReceived(DoctorInfo doctorinfo) {
                txtcost.setText(doctorinfo.getCost()+"");
                txtdescription.setText(doctorinfo.getDescription()+"");
                txtnezamcode.setText(doctorinfo.getNezamPezeshki()+"");
                doctorid=doctorinfo.getId();
            }
        });
        _cityapiservice.getCitiesString(new CityApiService.OnCitiesStringReceived() {
            @Override
            public void onReceived(ListCity city) {

                city_name=new String[city.getCityname().length];
                cityid=new Integer[city.getCityname().length];
                city_name=city.getCityname();
                cityid=city.getCityid();

                ArrayAdapter aa = new ArrayAdapter(ManagmentDoctorInfoActivity.this,android.R.layout.simple_spinner_item,city.getCityname());
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spncity.setAdapter(aa);

            }
        });

        _groupapiservice=new GroupApiService(this);
        _groupapiservice.getGroupsString(new GroupApiService.OnGroupsStringReceived() {
            @Override
            public void onReceived(ListGroup group) {
                group_name=new String[group.getGroupname().length];
                groupid=new Integer[group.getGroupname().length];
                group_name=group.getGroupname();
                groupid=group.getGroupid();

                ArrayAdapter aa = new ArrayAdapter(ManagmentDoctorInfoActivity.this,android.R.layout.simple_spinner_item,group.getGroupname());
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spngroup.setAdapter(aa);
            }
        });


        spncity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Object item = parent.getItemAtPosition(position);

                cityposition=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spngroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                groupposition=position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btndoctorupdateinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _doctorapiservice.postdoctor(doctorid, useritem.getName().toString(), cityid[cityposition], groupid[groupposition], txtdescription.getText().toString(), Integer.parseInt(txtcost.getText().toString()), txtnezamcode.getText().toString(), imgdoctorBitmap, new DoctorApiService.OnDoctorLoginReceived() {
                    @Override
                    public void onReceived(String checklogin) {
                        if (checklogin.equals("true")){

                            Toast.makeText(ManagmentDoctorInfoActivity.this,"با موفقیت تغییر کرد",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });



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
                imgdoctorBitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                imgdoctorBitmap=getResizedBitmap(imgdoctorBitmap,400,400);
                circleimage.setImageBitmap(imgdoctorBitmap);

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
