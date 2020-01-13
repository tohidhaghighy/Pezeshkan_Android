package com.example.salamatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salamatapp.AdaptorLayer.NoskheFinaly_Adaptor;
import com.example.salamatapp.DomainLayer.NoskheInfo;
import com.example.salamatapp.DomainLayer.NoskheList;
import com.example.salamatapp.PharmacyManagment.ManagmentPharmacyActivity;
import com.example.salamatapp.PharmacyManagment.ManagmentPharmacySendMassageActivity;
import com.example.salamatapp.PharmacyManagment.ManagmentPharmacyVisitActivity;
import com.example.salamatapp.ServiceLayer.NoskheApiService;
import com.example.salamatapp.ServiceLayer.PharmacyApiService;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NoskheManagmentPharmacyActivity extends AppCompatActivity {

    private Integer visitid;
    private NoskheApiService _noskheapi;
    private CircleImageView imgillness,imgdoctor;
    private RecyclerView noskhelistrecycle;
    private String pharmacyid="";
    private PharmacyApiService _pharmacyapi;
    private Intent i;
    private TextView txtillnessname,txtdoctorname,txtillnessdatebime,txtbimetypename,txtdoctornezampezeshki,txtdoctorpeigiri;

    private Button btnsendtopharmacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noskhe_managment_pharmacy);

        imgillness=(CircleImageView)findViewById(R.id.illnessimgcirclefornoskhe);
        imgdoctor=(CircleImageView)findViewById(R.id.doctorimgcirclefornoskhe);
        txtillnessname=(TextView) findViewById(R.id.txtillnessnamefornoskhe);
        txtdoctorname=(TextView) findViewById(R.id.txtdoctornameofnoskhe);
        txtillnessdatebime=(TextView) findViewById(R.id.txtillnessdatefornoskhe);
        txtbimetypename=(TextView) findViewById(R.id.txtillnesstypebimefornoskhe);
        txtdoctornezampezeshki=(TextView) findViewById(R.id.txtdoctornezampezeshkifornoskhe);
        txtdoctorpeigiri=(TextView) findViewById(R.id.txtdoctorpeigirifornoskhe);
        noskhelistrecycle=(RecyclerView)findViewById(R.id.noskhemanagmentlistrecycle);
        btnsendtopharmacy=(Button)findViewById(R.id.sendnoskhetopharmacymanagment);




        Bundle extras = getIntent().getExtras();

        if(extras == null) {
            visitid= 0;
            pharmacyid="";
        } else {
            visitid= extras.getInt("visitid");
            pharmacyid=extras.getString("pharmacy");
        }

        Log.e("id",visitid+"");

        _noskheapi=new NoskheApiService(this);
        _pharmacyapi=new PharmacyApiService(this);
        _noskheapi.getNoskheInfo(visitid, new NoskheApiService.OnNoskheInfoReceived() {
            @Override
            public void onReceived(NoskheInfo info) {
                Picasso.with(NoskheManagmentPharmacyActivity.this)
                        .load(info.getIllnessimage().toString())
                        .into(imgillness);
                Picasso.with(NoskheManagmentPharmacyActivity.this)
                        .load(info.getDoctorimage().toString())
                        .into(imgdoctor);
                txtillnessname.setText(info.getIllnessname().toString());
                txtbimetypename.setText(info.getTypeBime().toString()+" - "+info.getCodeBime().toString());
                txtdoctorname.setText(info.getDoctorname().toString());
                txtdoctornezampezeshki.setText(info.getDoctorNezamPezeshki().toString());
                txtdoctorpeigiri.setText(info.getPeigiriCode().toString());
            }
        });

        _noskheapi.getNoskheList(visitid, new NoskheApiService.OnNoskhelistReceived() {
            @Override
            public void onReceived(List<NoskheList> noskhelist) {

                Log.e("aray",noskhelist.toString());
                NoskheFinaly_Adaptor pa=new NoskheFinaly_Adaptor(NoskheManagmentPharmacyActivity.this, noskhelist);
                noskhelistrecycle.setLayoutManager(new LinearLayoutManager(NoskheManagmentPharmacyActivity.this,LinearLayoutManager.VERTICAL,false));
                noskhelistrecycle.setAdapter(pa);
            }
        });

        if (pharmacyid.equals("1")){
            btnsendtopharmacy.setText("ارسال پاسخ به بیمار");
            btnsendtopharmacy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i = new Intent(NoskheManagmentPharmacyActivity.this, ManagmentPharmacySendMassageActivity.class);
                    i.putExtra("visitid",visitid);
                    startActivity(i);
                    finish();
                }
            });
        }
        else {
            btnsendtopharmacy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    _pharmacyapi.postaddnoskhetopharmacy(visitid, new PharmacyApiService.OnPharmacyStringReceived() {
                        @Override
                        public void onReceived(String checkIsok) {
                            Toast.makeText(NoskheManagmentPharmacyActivity.this, "با موفقیت ارسال شد",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
        }


    }
}
