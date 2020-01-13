package com.example.salamatapp.AdaptorLayer;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salamatapp.DomainLayer.Group;
import com.example.salamatapp.DomainLayer.User;
import com.example.salamatapp.ListDoctorActivity;
import com.example.salamatapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GroupItem_Adaptor extends RecyclerView.Adapter<GroupItem_Adaptor.GroupItem_ViewHolder> {


    private Context context;
    private List<Group> allgroup;

    public GroupItem_Adaptor(Context context, List<Group> allgroup){
        this.context = context;
        this.allgroup = allgroup;
    }

    @NonNull
    @Override
    public GroupItem_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_group_one_item,viewGroup,false);
        return new GroupItem_Adaptor.GroupItem_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupItem_ViewHolder groupItem_viewHolder, int i) {
        Group user=allgroup.get(i);
        groupItem_viewHolder.txtname.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return allgroup.size();
    }


    public class GroupItem_ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtname;
        private ImageButton btngroup;
        public GroupItem_ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=(TextView)itemView.findViewById(R.id.txtgroup_name);
            btngroup=(ImageButton)itemView.findViewById(R.id.btngrouplist);
            btngroup.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent i = new Intent(context, ListDoctorActivity.class);

                    i.putExtra("type",9);
                    i.putExtra("groupdoctor",allgroup.get(getAdapterPosition()).getId());
                    context.startActivity(i);

                }
            });
        }

    }
}

