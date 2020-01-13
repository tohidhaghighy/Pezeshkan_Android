package com.example.salamatapp.AdaptorLayer.DoctorManagment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.salamatapp.AdaptorLayer.IllnessManagment.IllnessVisit_Adaptor;
import com.example.salamatapp.ChatManagment.ChatIllnessManagmentActivity;
import com.example.salamatapp.DoctorManagment.ManagmentDoctorIllnessAnswerQuestionActivity;
import com.example.salamatapp.DoctorManagment.ManagmentDoctorNoskheActivity;
import com.example.salamatapp.DomainLayer.Doctor.DoctorVisit;
import com.example.salamatapp.DomainLayer.Illness.IllnessVisitList;
import com.example.salamatapp.R;
import com.example.salamatapp.bottomActivity;

import java.util.List;

public class DoctorVisit_Adaptor extends RecyclerView.Adapter<DoctorVisit_Adaptor.DoctorVisit_ViewHolder> {


    private Context context;
    private List<DoctorVisit> allvisit;

    public DoctorVisit_Adaptor(Context context, List<DoctorVisit> allvisit){
        this.context = context;
        this.allvisit = allvisit;
    }

    @NonNull
    @Override
    public DoctorVisit_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_doctor_visitlist_managment,viewGroup,false);
        return new DoctorVisit_Adaptor.DoctorVisit_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorVisit_ViewHolder doctorVisit_viewHolder, int i) {

        DoctorVisit dvisit=allvisit.get(i);
        doctorVisit_viewHolder.txttime.setText(dvisit.getTimeDayDoctorDes());
        doctorVisit_viewHolder.txtdate.setText(dvisit.getReserveDatetime());
        doctorVisit_viewHolder.txtillnessname.setText(dvisit.getIllnessname());
        doctorVisit_viewHolder.txtpeigiri.setText(dvisit.getPeigiriCode());
    }

    @Override
    public int getItemCount() {
        return allvisit.size();
    }


    public class DoctorVisit_ViewHolder extends RecyclerView.ViewHolder{

        TextView txtillnessname,txtdate,txttime,txtpeigiri;
        Button btnchat,btnnoskhe,btnquestion;
        public DoctorVisit_ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtillnessname=(TextView)itemView.findViewById(R.id.txtdoctornameitemmanagmentdoctor);
            txtpeigiri=(TextView)itemView.findViewById(R.id.txtdoctorparvandecodeitemmanagmentdoctor);
            txtdate=(TextView)itemView.findViewById(R.id.txtdateitemmanagmentdoctor);
            txttime=(TextView)itemView.findViewById(R.id.txttimeitemmanagmentdoctor);

            btnchat=(Button)itemView.findViewById(R.id.btnchatroomitemmanagmentdoctor);
            btnnoskhe=(Button)itemView.findViewById(R.id.btnnoskheitemmanagmentdoctor);
            btnquestion=(Button)itemView.findViewById(R.id.btnquestionitemmanagmentdoctor);
            btnchat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ChatIllnessManagmentActivity.class);
                    i.putExtra("visitid",allvisit.get(getAdapterPosition()).getId());
                    i.putExtra("roleid","false");
                    context.startActivity(i);
                }
            });

            btnnoskhe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ManagmentDoctorNoskheActivity.class);
                    i.putExtra("visitid",allvisit.get(getAdapterPosition()).getId());
                    context.startActivity(i);
                }
            });

            btnquestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ManagmentDoctorIllnessAnswerQuestionActivity.class);
                    i.putExtra("visitid",allvisit.get(getAdapterPosition()).getId());
                    context.startActivity(i);
                }
            });



        }

    }
}


