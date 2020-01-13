package com.example.salamatapp.AdaptorLayer.IllnessManagment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.salamatapp.DomainLayer.Illness.IllnessJudge;
import com.example.salamatapp.DomainLayer.Illness.IllnessPharmacyList;
import com.example.salamatapp.DomainLayer.Illness.IllnessVisitList;
import com.example.salamatapp.NoskheManagmentIllnessActivity;
import com.example.salamatapp.R;

import java.util.List;

public class IllnessPharmacy_Adaptor extends RecyclerView.Adapter<IllnessPharmacy_Adaptor.IllnessPharmacy_ViewHolder> {


    private Context context;
    private List<IllnessPharmacyList> allpharmacy;

    public IllnessPharmacy_Adaptor(Context context, List<IllnessPharmacyList> allpharmacy){
        this.context = context;
        this.allpharmacy = allpharmacy;
    }

    @NonNull
    @Override
    public IllnessPharmacy_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_illness_pharmacylist_managment,viewGroup,false);
        return new IllnessPharmacy_Adaptor.IllnessPharmacy_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IllnessPharmacy_ViewHolder illnessPharmacy_viewHolder, int i) {
        IllnessPharmacyList illnesspharmacy=allpharmacy.get(i);
        illnessPharmacy_viewHolder.txtname.setText(illnesspharmacy.getPharmacyname());
        illnessPharmacy_viewHolder.txtdescription.setText(illnesspharmacy.getDescription());
    }

    @Override
    public int getItemCount() {
        return allpharmacy.size();
    }


    public class IllnessPharmacy_ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtname,txtdescription;
        private Button btnnoskhe;
        public IllnessPharmacy_ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=(TextView)itemView.findViewById(R.id.txtillnesspharmacynamemanagmentitem);
            txtdescription=(TextView)itemView.findViewById(R.id.txtillnesspharmacydescriptionmanagmentitem);
            btnnoskhe=(Button)itemView.findViewById(R.id.btnpillnesspharamacymanagmentitem);
            btnnoskhe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, NoskheManagmentIllnessActivity.class);
                    i.putExtra("visitid",allpharmacy.get(getAdapterPosition()).getVisitId());
                    context.startActivity(i);
                }
            });


        }

    }
}
