package com.example.salamatapp.AdaptorLayer.DoctorManagment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.salamatapp.DoctorManagment.ManagmentDoctorQuestion2Activity;
import com.example.salamatapp.DoctorManagment.ManagmentDoctorVisitDoctorActivity;
import com.example.salamatapp.DomainLayer.Doctor.DoctorVisit;
import com.example.salamatapp.DomainLayer.Doctor.DoctorVisitSelect;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.DoctorVisitApiService;

import java.util.List;

public class DoctorVisitTime_Adaptor extends RecyclerView.Adapter<DoctorVisitTime_Adaptor.DoctorVisitTime_ViewHolder> {


    private Context context;
    private List<DoctorVisitSelect> allvisit;
    private DoctorVisitApiService _doctorapi;
    private Intent i;

    public DoctorVisitTime_Adaptor(Context context, List<DoctorVisitSelect> allvisit){
        this.context = context;
        this.allvisit = allvisit;
    }

    @NonNull
    @Override
    public DoctorVisitTime_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_doctor_visittime_managment,viewGroup,false);
        return new DoctorVisitTime_Adaptor.DoctorVisitTime_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorVisitTime_ViewHolder doctorVisitTime_viewHolder, int i) {
        DoctorVisitSelect doctorvisit=allvisit.get(i);
        doctorVisitTime_viewHolder.txtcount.setText(doctorvisit.getCount().toString());
        doctorVisitTime_viewHolder.txtdescription.setText(doctorvisit.getDescription().toString());
        doctorVisitTime_viewHolder.txtdate.setText(doctorvisit.getDate().toString());
    }

    @Override
    public int getItemCount() {
        return allvisit.size();
    }


    public class DoctorVisitTime_ViewHolder extends RecyclerView.ViewHolder{

        TextView txtdate,txtdescription,txtcount;
        Button btndelete;

        public DoctorVisitTime_ViewHolder(@NonNull View itemView) {
            super(itemView);

            _doctorapi=new DoctorVisitApiService(context);
            txtdate=(TextView)itemView.findViewById(R.id.txtdateofvisitdoctortimemanagment);
            txtdescription=(TextView)itemView.findViewById(R.id.txtdescriptionofvisitdoctortimemanagment);
            txtcount=(TextView)itemView.findViewById(R.id.txtcountofvisitdoctortimemanagment);
            btndelete=(Button)itemView.findViewById(R.id.btndeleteofvisitdoctortimemangment);
            btndelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _doctorapi.postdeletevisitlist(allvisit.get(getAdapterPosition()).getId(), new DoctorVisitApiService.OnStringResponceVisitTimeReceived() {
                        @Override
                        public void onReceived(String doctorvisitstring) {
                            i = new Intent(context, ManagmentDoctorVisitDoctorActivity.class);
                            context.startActivity(i);
                            ((Activity)context).finish();
                        }
                    });
                }
            });



        }

    }
}



