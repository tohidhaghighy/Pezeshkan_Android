package com.example.salamatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.salamatapp.AdaptorLayer.NoskheFinaly_Adaptor;
import com.example.salamatapp.AdaptorLayer.PharmacyJudgeAdaptor;
import com.example.salamatapp.DomainLayer.NoskheInfo;
import com.example.salamatapp.DomainLayer.NoskheList;
import com.example.salamatapp.ServiceLayer.NoskheApiService;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NoskheManagmentIllnessActivity extends AppCompatActivity {

    private Integer visitid;
    private NoskheApiService _noskheapi;
    private CircleImageView imgillness,imgdoctor;
    private RecyclerView noskhelistrecycle;
    private TextView txtillnessname,txtdoctorname,txtillnessdatebime,txtbimetypename,txtdoctornezampezeshki,txtdoctorpeigiri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noskhe_managment_illness);
        imgillness=(CircleImageView)findViewById(R.id.illnessimgcirclefornoskhe);
        imgdoctor=(CircleImageView)findViewById(R.id.doctorimgcirclefornoskhe);
        txtillnessname=(TextView) findViewById(R.id.txtillnessnamefornoskhe);
        txtdoctorname=(TextView) findViewById(R.id.txtdoctornameofnoskhe);
        txtillnessdatebime=(TextView) findViewById(R.id.txtillnessdatefornoskhe);
        txtbimetypename=(TextView) findViewById(R.id.txtillnesstypebimefornoskhe);
        txtdoctornezampezeshki=(TextView) findViewById(R.id.txtdoctornezampezeshkifornoskhe);
        txtdoctorpeigiri=(TextView) findViewById(R.id.txtdoctorpeigirifornoskhe);
        noskhelistrecycle=(RecyclerView)findViewById(R.id.noskhemanagmentlistrecycle);

        Bundle extras = getIntent().getExtras();

        if(extras == null) {
            visitid= 0;
        } else {
            visitid= extras.getInt("visitid");
        }

        Log.e("id",visitid+"");

        _noskheapi=new NoskheApiService(this);
        _noskheapi.getNoskheInfo(visitid, new NoskheApiService.OnNoskheInfoReceived() {
            @Override
            public void onReceived(NoskheInfo info) {
                Picasso.with(NoskheManagmentIllnessActivity.this)
                        .load(info.getIllnessimage().toString())
                        .into(imgillness);
                Picasso.with(NoskheManagmentIllnessActivity.this)
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

                NoskheFinaly_Adaptor pa=new NoskheFinaly_Adaptor(NoskheManagmentIllnessActivity.this, noskhelist);
                noskhelistrecycle.setLayoutManager(new LinearLayoutManager(NoskheManagmentIllnessActivity.this,LinearLayoutManager.VERTICAL,false));
                noskhelistrecycle.setAdapter(pa);
            }
        });
    }
}
