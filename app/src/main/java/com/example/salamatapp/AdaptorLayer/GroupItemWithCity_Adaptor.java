package com.example.salamatapp.AdaptorLayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.salamatapp.DomainLayer.Group;
import com.example.salamatapp.ListDoctorActivity;
import com.example.salamatapp.R;

import java.util.List;

public class GroupItemWithCity_Adaptor extends RecyclerView.Adapter<GroupItemWithCity_Adaptor.GroupItemWithCity_ViewHolder> {


    private Context context;
    private List<Group> allgroup;
    private Integer cityid;

    public GroupItemWithCity_Adaptor(Context context, List<Group> allgroup,Integer cityid){
        this.context = context;
        this.allgroup = allgroup;
        this.cityid=cityid;
    }

    @NonNull
    @Override
    public GroupItemWithCity_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_group_one_item,viewGroup,false);
        return new GroupItemWithCity_Adaptor.GroupItemWithCity_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupItemWithCity_ViewHolder groupItemWithCity_viewHolder, int i) {
        Group user=allgroup.get(i);
        groupItemWithCity_viewHolder.txtname.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return allgroup.size();
    }


    public class GroupItemWithCity_ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtname;
        private ImageButton btngroup;
        public GroupItemWithCity_ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=(TextView)itemView.findViewById(R.id.txtgroup_name);
            btngroup=(ImageButton)itemView.findViewById(R.id.btngrouplist);
            btngroup.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent i = new Intent(context, ListDoctorActivity.class);

                    i.putExtra("type",7);
                    i.putExtra("groupiditem",allgroup.get(getAdapterPosition()).getId());
                    i.putExtra("cityid",cityid);
                    context.startActivity(i);

                }
            });
        }

    }
}

