package com.example.salamatapp.AdaptorLayer;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.salamatapp.DomainLayer.City;
import com.example.salamatapp.DomainLayer.Violation;
import com.example.salamatapp.ListGroupCityDoctorActivity;
import com.example.salamatapp.R;

import java.util.List;

public class ViolationAdaptor extends RecyclerView.Adapter<ViolationAdaptor.Violation_ViewHolder>{

    private Context context;
    private List<Violation> allviolation;

    public ViolationAdaptor(Context context, List<Violation> allviolation){
        this.context = context;
        this.allviolation = allviolation;
    }

    @NonNull
    @Override
    public Violation_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_violation_layout,viewGroup,false);
        return new ViolationAdaptor.Violation_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Violation_ViewHolder violation_viewHolder, int i) {
        Violation violation=allviolation.get(i);
        violation_viewHolder.txtnumber.setText(violation.getNumber());
        violation_viewHolder.txtask.setText(violation.getDescriotion());
        try{
            violation_viewHolder.txtanswer.setText(violation.getAnswerAdmin());
        }catch (Exception ex){
            violation_viewHolder.txtanswer.setText("هنوز پاسخ داده نشده است");
        }
        if (violation.getAnswerAdmin().equals("null")){
            violation_viewHolder.txtanswer.setText("هنوز پاسخ داده نشده است");
        }

    }

    @Override
    public int getItemCount() {
        return allviolation.size();
    }


    public class Violation_ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtnumber,txtask,txtanswer;
        public Violation_ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtnumber=(TextView)itemView.findViewById(R.id.txtnumber);
            txtask=(TextView)itemView.findViewById(R.id.txtasktext);
            txtanswer=(TextView)itemView.findViewById(R.id.txtanswertext);
        }

    }

}
