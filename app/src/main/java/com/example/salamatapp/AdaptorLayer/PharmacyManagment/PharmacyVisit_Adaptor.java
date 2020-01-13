package com.example.salamatapp.AdaptorLayer.PharmacyManagment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.salamatapp.AdaptorLayer.JudgeManagment.JudgeVisit_Adaptor;
import com.example.salamatapp.DomainLayer.Judge.JudgeVisitList;
import com.example.salamatapp.DomainLayer.Pharmacy.PharmacyVisitList;
import com.example.salamatapp.ListDoctorActivity;
import com.example.salamatapp.NoskheManagmentIllnessActivity;
import com.example.salamatapp.NoskheManagmentPharmacyActivity;
import com.example.salamatapp.PharmacyManagment.ManagmentPharmacyActivity;
import com.example.salamatapp.PharmacyManagment.ManagmentPharmacyVisitActivity;
import com.example.salamatapp.R;

import java.util.List;

public class PharmacyVisit_Adaptor extends RecyclerView.Adapter<PharmacyVisit_Adaptor.PharmacyVisit_ViewHolder> {


    private Context context;
    private List<PharmacyVisitList> allvisit;
    private Intent i;

    public PharmacyVisit_Adaptor(Context context, List<PharmacyVisitList> allvisit){
        this.context = context;
        this.allvisit = allvisit;
    }

    @NonNull
    @Override
    public PharmacyVisit_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_pharmacy_visit_managment,viewGroup,false);
        return new PharmacyVisit_Adaptor.PharmacyVisit_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacyVisit_ViewHolder pharmacyVisit_viewHolder, int i) {

        PharmacyVisitList pharmacyvisit=allvisit.get(i);
        pharmacyVisit_viewHolder.txtdoctorname.setText(pharmacyvisit.getDoctorName().toString());
        pharmacyVisit_viewHolder.txtillnessname.setText(pharmacyvisit.getIllnessName().toString());
    }

    @Override
    public int getItemCount() {
        return allvisit.size();
    }


    public class PharmacyVisit_ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtillnessname,txtdoctorname;
        private Button btnnoskhe;

        public PharmacyVisit_ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtillnessname=(TextView)itemView.findViewById(R.id.txtillnessnamepharmacyillnessmanagment);
            txtdoctorname=(TextView)itemView.findViewById(R.id.txtdoctornamepharmacyillnessmanagment);
            btnnoskhe=(Button)itemView.findViewById(R.id.btnnoskhepharmacyillnessmanagment);
            btnnoskhe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    i = new Intent(context, NoskheManagmentPharmacyActivity.class);
                    i.putExtra("visitid",allvisit.get(getAdapterPosition()).getVisitId());
                    i.putExtra("pharmacy","1");
                    context.startActivity(i);
                }
            });

        }

    }
}


