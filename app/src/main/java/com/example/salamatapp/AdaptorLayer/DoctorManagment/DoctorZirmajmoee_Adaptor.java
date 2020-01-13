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

import com.example.salamatapp.DoctorManagment.ManagmentDoctorDragActivity;
import com.example.salamatapp.DomainLayer.City;
import com.example.salamatapp.DomainLayer.Doctor.DoctorDrag;
import com.example.salamatapp.DomainLayer.Doctor.DoctorZir;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.DoctorDragApiService;

import java.util.List;

public class DoctorZirmajmoee_Adaptor extends RecyclerView.Adapter<DoctorZirmajmoee_Adaptor.DoctorZirmajmoee_ViewHolder>{
    private Context context;
    private List<DoctorZir> alluser;
    private Intent i;

    public DoctorZirmajmoee_Adaptor(Context context, List<DoctorZir> alluser){
        this.context = context;
        this.alluser = alluser;
    }

    @NonNull
    @Override
    public DoctorZirmajmoee_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_userzirmajmoee_one,viewGroup,false);
        return new DoctorZirmajmoee_Adaptor.DoctorZirmajmoee_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorZirmajmoee_ViewHolder doctorZirmajmoee_viewHolder, int i) {
        DoctorZir drag=alluser.get(i);
        doctorZirmajmoee_viewHolder.txtname.setText(drag.getName());
    }

    @Override
    public int getItemCount() {
        return alluser.size();
    }

    public class DoctorZirmajmoee_ViewHolder extends RecyclerView.ViewHolder{

        TextView txtname;
        public DoctorZirmajmoee_ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=(TextView)itemView.findViewById(R.id.txtlistusername);



        }

    }

}
