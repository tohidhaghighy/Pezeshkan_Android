package com.example.salamatapp.FragmentLayer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salamatapp.AdaptorLayer.CityItem_Adaptor;
import com.example.salamatapp.AdaptorLayer.TimeVisitDoctor_Adaptor;
import com.example.salamatapp.DomainLayer.SaveUserItem;
import com.example.salamatapp.DomainLayer.User;
import com.example.salamatapp.DomainLayer.VisitTimeDoctor;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.DataFakeGenarator;
import com.example.salamatapp.ServiceLayer.DoctorApiService;
import com.example.salamatapp.ServiceLayer.UserApiService;
import com.example.salamatapp.SharePrefrence.UserSharePrefrenceManager;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DoctorDetailFragment extends Fragment {

    ImageView imgdoctor;
    Integer doctorid;
    TextView txtname,txttakhasos,txtcity,txtdescription,txtcost;
    private UserSharePrefrenceManager usermanager;
    private SaveUserItem useritem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_detail_doctor,container,false);
        DoctorApiService _doctorapi=new DoctorApiService(view.getContext());


        usermanager=new UserSharePrefrenceManager(view.getContext());
        useritem=new SaveUserItem();
        useritem=usermanager.getUser();


        imgdoctor=(ImageView)view.findViewById(R.id.imgcircleofdoctor);
        txtname=(TextView)view.findViewById(R.id.txtnameofdoctor);
        txtcity=(TextView)view.findViewById(R.id.txtcityofdoctor);
        txtcost=(TextView)view.findViewById(R.id.txtcostofdoctor);
        txttakhasos=(TextView)view.findViewById(R.id.txttakhasosofdoctor);
        txtdescription=(TextView)view.findViewById(R.id.txtdescriptionofdoctor);

        Bundle args = getActivity().getIntent().getExtras();
        doctorid= args.getInt("doctorid");

        _doctorapi.getDoctor(doctorid, new DoctorApiService.OnDoctorReceived() {
            @Override
            public void onReceived(User user) {
                txtname.setText(user.getName());
                txtcity.setText(user.getCity());
                txtcost.setText(user.getVisitCost());
                txtdescription.setText(user.getDescription());
                txttakhasos.setText(user.getTakhasos());
                Picasso.with(view.getContext()).load("https://c7.uihere.com/files/348/800/890/computer-icons-avatar-user-login-avatar.jpg").into(imgdoctor);
            }
        });

        if (useritem.getType()==3) {
            _doctorapi.getDoctorTimes(doctorid, new DoctorApiService.OnDoctorTimeReceived() {
                @Override
                public void onReceived(List<VisitTimeDoctor> visittimedoctor) {
                    RecyclerView rv=(RecyclerView)view.findViewById(R.id.visittimelistrecyvleview);
                    TimeVisitDoctor_Adaptor visittimes=new TimeVisitDoctor_Adaptor(view.getContext(), visittimedoctor,useritem.getNumber().toString(),doctorid);
                    rv.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
                    rv.setAdapter(visittimes);
                }

            });
        }else {
            Toast.makeText(view.getContext(),"فقط بیمار میتواند وقت رزرو کند",Toast.LENGTH_SHORT).show();
        }



        ScrollView scrollView=(ScrollView)view.findViewById(R.id.scrolldoctordetail);
        scrollView.smoothScrollBy(0,0);
        return view;
    }
}
