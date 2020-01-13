package com.example.salamatapp.AdaptorLayer.IllnessManagment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.salamatapp.AdaptorLayer.CityItem_Adaptor;
import com.example.salamatapp.DomainLayer.City;
import com.example.salamatapp.DomainLayer.Illness.IllnessVisitList;
import com.example.salamatapp.IllnessManagment.ManagmentIllnessQuestionListActivity;
import com.example.salamatapp.ListDoctorActivity;
import com.example.salamatapp.NoskheManagmentIllnessActivity;
import com.example.salamatapp.NoskheManagmentPharmacyActivity;
import com.example.salamatapp.R;
import com.example.salamatapp.bottomActivity;

import java.util.List;

public class IllnessVisit_Adaptor extends RecyclerView.Adapter<IllnessVisit_Adaptor.IllnessVisit_ViewHolder> {


    private Context context;
    private List<IllnessVisitList> allvisit;

    public IllnessVisit_Adaptor(Context context, List<IllnessVisitList> allvisit){
        this.context = context;
        this.allvisit = allvisit;
    }

    @NonNull
    @Override
    public IllnessVisit_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_illness_visitlist_managment,viewGroup,false);
        return new IllnessVisit_Adaptor.IllnessVisit_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IllnessVisit_ViewHolder illnessVisit_viewHolder, int i) {
        IllnessVisitList illnessVisitList=allvisit.get(i);
        illnessVisit_viewHolder.txtdoctorname.setText(illnessVisitList.getDoctorname());
        illnessVisit_viewHolder.txtnobat.setText(illnessVisitList.getNobat());
        illnessVisit_viewHolder.txtdate.setText(illnessVisitList.getReserveDatetime());
        illnessVisit_viewHolder.txttime.setText(illnessVisitList.getTimeDayDoctorDes());
    }

    @Override
    public int getItemCount() {
        return allvisit.size();
    }


    public class IllnessVisit_ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtdoctorname,txtdate,txttime;
        private Button txtnobat,btnchatroom,btnnoskhe;

        public IllnessVisit_ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtdoctorname=(TextView)itemView.findViewById(R.id.txtdoctornameitemmanagmentillness);
            txtdate=(TextView)itemView.findViewById(R.id.txtdateitemmanagmentillness);
            txttime=(TextView)itemView.findViewById(R.id.txttimeitemmanagmentillness);
            txtnobat=(Button)itemView.findViewById(R.id.txtnobatitemmanagmentillness);
            btnchatroom=(Button)itemView.findViewById(R.id.btnchatroomitemmanagmentillness);
            btnnoskhe=(Button)itemView.findViewById(R.id.btnnoskheitemmanagmentillness);
            btnchatroom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ManagmentIllnessQuestionListActivity.class);
                    i.putExtra("visitid",allvisit.get(getAdapterPosition()).getId());
                    context.startActivity(i);
                }
            });

            btnnoskhe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, NoskheManagmentPharmacyActivity.class);
                    i.putExtra("visitid",allvisit.get(getAdapterPosition()).getId());
                    i.putExtra("pharmacy","2");
                    context.startActivity(i);
                }
            });

        }

    }
}


