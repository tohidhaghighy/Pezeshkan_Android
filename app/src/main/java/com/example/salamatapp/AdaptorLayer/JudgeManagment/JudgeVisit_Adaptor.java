package com.example.salamatapp.AdaptorLayer.JudgeManagment;

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
import com.example.salamatapp.ChatManagment.ChatJudgeManagmentActivity;
import com.example.salamatapp.DomainLayer.Illness.IllnessVisitList;
import com.example.salamatapp.DomainLayer.Judge.JudgeVisitList;
import com.example.salamatapp.R;
import com.example.salamatapp.bottomActivity;

import java.util.List;

public class JudgeVisit_Adaptor extends RecyclerView.Adapter<JudgeVisit_Adaptor.JudgveVisit_ViewHolder> {


    private Context context;
    private List<JudgeVisitList> allvisit;

    public JudgeVisit_Adaptor(Context context, List<JudgeVisitList> allvisit){
        this.context = context;
        this.allvisit = allvisit;
    }

    @NonNull
    @Override
    public JudgveVisit_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_judge_visit_managment,viewGroup,false);
        return new JudgeVisit_Adaptor.JudgveVisit_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JudgveVisit_ViewHolder judgveVisit_viewHolder, int i) {

        JudgeVisitList getvisit=allvisit.get(i);
        judgveVisit_viewHolder.txtillnessname.setText(getvisit.getIllnessname().toString());
        judgveVisit_viewHolder.txtillnesscost.setText(getvisit.getCost().toString());
    }

    @Override
    public int getItemCount() {
        return allvisit.size();
    }

    public class JudgveVisit_ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtillnessname,txtillnesscost;
        private Button btnstartchat;

        public JudgveVisit_ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtillnessname=(TextView)itemView.findViewById(R.id.txtnamejudgeillnessmanagment);
            txtillnesscost=(TextView)itemView.findViewById(R.id.txtcostjudgeillnessmanagment);
            btnstartchat=(Button)itemView.findViewById(R.id.btnstartchatjudgeillnessmanagment);
            btnstartchat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ChatJudgeManagmentActivity.class);
                    i.putExtra("visitid",allvisit.get(getAdapterPosition()).getId());
                    i.putExtra("roleid","false");
                    context.startActivity(i);
                }
            });

        }

    }
}


