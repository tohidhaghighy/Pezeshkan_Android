package com.example.salamatapp.AdaptorLayer;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.salamatapp.DomainLayer.User;
import com.example.salamatapp.ListDoctorActivity;
import com.example.salamatapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PharmacyJudgeAdaptor extends RecyclerView.Adapter<PharmacyJudgeAdaptor.PharmacyJudge_ViewHolder> {


    private Context context;
    private List<User> alluser;

    public PharmacyJudgeAdaptor(Context context, List<User> allgroup){
        this.context = context;
        this.alluser = allgroup;
    }

    @NonNull
    @Override
    public PharmacyJudge_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_user_one_item,viewGroup,false);
        return new PharmacyJudgeAdaptor.PharmacyJudge_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacyJudge_ViewHolder pharmacyJudge_viewHolder, int i) {
        User user=alluser.get(i);
        pharmacyJudge_viewHolder.txtname.setText(user.getName());
        pharmacyJudge_viewHolder.txtdescriotion.setText(user.getDescription());
        pharmacyJudge_viewHolder.txttakhasos.setText(user.getTakhasos());
        Picasso.with(context).load(user.getImage()).into(pharmacyJudge_viewHolder.circleimageview);
    }

    @Override
    public int getItemCount() {
        return alluser.size();
    }


    public class PharmacyJudge_ViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView circleimageview;
        private TextView txtname;
        private TextView txttakhasos;
        private TextView txtdescriotion;
        private LinearLayout llbtndoctoritem;
        public PharmacyJudge_ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=(TextView)itemView.findViewById(R.id.txtname);
            txttakhasos=(TextView)itemView.findViewById(R.id.txttakhasos);
            txtdescriotion=(TextView)itemView.findViewById(R.id.txtdescription);
            circleimageview=(CircleImageView)itemView.findViewById(R.id.imgcircleuser);
        }

    }
}

