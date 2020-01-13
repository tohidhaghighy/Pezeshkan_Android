package com.example.salamatapp.PharmacyManagment;

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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salamatapp.DoctorManagment.ManagmentDoctorInfoActivity;
import com.example.salamatapp.DomainLayer.ListCity;
import com.example.salamatapp.DomainLayer.Pharmacy.PharmacyInfo;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.JudgeManagment.ManagmentJudgeInfoActivity;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.CityApiService;
import com.example.salamatapp.ServiceLayer.PharmacyApiService;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;

import de.hdodenhof.circleimageview.CircleImageView;

public class ManagmentPharmacyInfoActivity extends AppCompatActivity {

    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;
    private Bitmap imgpharmacybitmap;
    private CircleImageView circleimg;
    private TextView txtname,txtnumber,txtdes,txtaddress;
    private Spinner spncity;
    private Button btnupdate;
    private CityApiService _cityapiservice;
    private String[] city_name;
    private Integer[] cityid;
    private Integer cityposition;
    private PharmacyApiService _pharmacyservice;
    private ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment_pharmacy_info);
        btnback=(ImageView)findViewById(R.id.btnbacklogin);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        usermanager=new UserSharePrefrenceManager(this);
        useritem=new SaveUserItem();
        useritem=usermanager.getUser();


        circleimg=(CircleImageView)findViewById(R.id.circleimgpharmacylistinfo);
        txtname=(TextView)findViewById(R.id.txtnameofpharmacymanagment);
        txtnumber=(TextView)findViewById(R.id.txtnumberofpharmacymanagment);
        txtaddress=(TextView)findViewById(R.id.txtaddressofpharmacymanagment);
        txtdes=(TextView)findViewById(R.id.txtdescriptionofpharmacymanagment);
        spncity=(Spinner)findViewById(R.id.spnpharmacyofmanagment);
        btnupdate=(Button)findViewById(R.id.btnupdateofpharmacymanagment);

        txtname.setText(useritem.getName()+"");
        txtnumber.setText(useritem.getNumber()+"");
        _cityapiservice=new CityApiService(this);

        _cityapiservice.getCitiesString(new CityApiService.OnCitiesStringReceived() {
            @Override
            public void onReceived(ListCity city) {
                city_name=new String[city.getCityname().length];
                cityid=new Integer[city.getCityname().length];
                city_name=city.getCityname();
                cityid=city.getCityid();

                ArrayAdapter aa = new ArrayAdapter(ManagmentPharmacyInfoActivity.this,android.R.layout.simple_spinner_item,city.getCityname());
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spncity.setAdapter(aa);

            }
        });

        spncity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cityposition=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        _pharmacyservice=new PharmacyApiService(this);
        _pharmacyservice.getPharmacyInfo(useritem.getNumber().toString(), new PharmacyApiService.OnPharmacyReceived() {
            @Override
            public void onReceived(PharmacyInfo pharmacy) {
                txtaddress.setText(pharmacy.getAddress().toString());
                txtdes.setText(pharmacy.getDescription().toString());
            }
        });

        circleimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Imageselect();
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _pharmacyservice.postpharmacy(useritem.getNumber().toString(), useritem.getName().toString(), cityid[cityposition], txtdes.getText().toString(), txtaddress.getText().toString(), imgpharmacybitmap, new PharmacyApiService.OnPharmacyStringReceived() {
                    @Override
                    public void onReceived(String checkIsok) {
                        if (checkIsok.equals("true")){
                            Toast.makeText(ManagmentPharmacyInfoActivity.this,"با موفقیت تغییر کرد",Toast.LENGTH_LONG).show();
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
                imgpharmacybitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                imgpharmacybitmap=getResizedBitmap(imgpharmacybitmap,400,400);
                circleimg.setImageBitmap(imgpharmacybitmap);

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
