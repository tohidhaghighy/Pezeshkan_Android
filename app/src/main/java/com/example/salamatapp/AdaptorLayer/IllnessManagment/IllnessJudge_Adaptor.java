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

import com.example.salamatapp.ChatManagment.ChatJudgeManagmentActivity;
import com.example.salamatapp.DomainLayer.Illness.IllnessJudge;
import com.example.salamatapp.DomainLayer.Illness.IllnessVisitList;
import com.example.salamatapp.NoskheManagmentIllnessActivity;
import com.example.salamatapp.R;
import com.example.salamatapp.bottomActivity;

import java.util.List;

public class IllnessJudge_Adaptor extends RecyclerView.Adapter<IllnessJudge_Adaptor.IllnessJudge_ViewHolder> {


    private Context context;
    private List<IllnessJudge> alljudge;

    public IllnessJudge_Adaptor(Context context, List<IllnessJudge> alljudge){
        this.context = context;
        this.alljudge = alljudge;
    }

    @NonNull
    @Override
    public IllnessJudge_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_illness_judgelist_managment,viewGroup,false);
        return new IllnessJudge_Adaptor.IllnessJudge_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IllnessJudge_ViewHolder illnessJudge_viewHolder, int i) {
        IllnessJudge illnessJudge=alljudge.get(i);
        illnessJudge_viewHolder.txtname.setText(illnessJudge.getIllnessname());
        illnessJudge_viewHolder.txtcost.setText(illnessJudge.getCost()+"");
    }

    @Override
    public int getItemCount() {
        return alljudge.size();
    }


    public class IllnessJudge_ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtname,txtcost;
        private Button btnchatroom;
        public IllnessJudge_ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=(TextView)itemView.findViewById(R.id.txtillnessnamemanagmentitem);
            txtcost=(TextView)itemView.findViewById(R.id.txtillnesscostmanagmentitem);
            btnchatroom=(Button)itemView.findViewById(R.id.btnillnessjudgechatmanagmentitem);
            btnchatroom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ChatJudgeManagmentActivity.class);
                    i.putExtra("visitid",alljudge.get(getAdapterPosition()).getId());
                    i.putExtra("roleid","true");
                    context.startActivity(i);
                }
            });


        }

    }
}