package com.example.salamatapp.AdaptorLayer;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salamatapp.DomainLayer.User;
import com.example.salamatapp.ListDoctorActivity;
import com.example.salamatapp.R;
import com.example.salamatapp.SplashActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Doctoritem_Adaptor extends RecyclerView.Adapter<Doctoritem_Adaptor.Doctoritem_ViewHolder> {


    private Context context;
    private List<User> alluser;

    public Doctoritem_Adaptor(Context context, List<User> allgroup){
        this.context = context;
        this.alluser = allgroup;
    }

    @NonNull
    @Override
    public Doctoritem_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_user_one_item,viewGroup,false);
        return new Doctoritem_Adaptor.Doctoritem_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Doctoritem_ViewHolder doctoritem_viewHolder, int i) {
        User user=alluser.get(i);
        doctoritem_viewHolder.txtname.setText(user.getName());
        doctoritem_viewHolder.txtdescriotion.setText(user.getDescription());
        doctoritem_viewHolder.txttakhasos.setText(user.getTakhasos());
        Picasso.with(context).load(user.getImage()).into(doctoritem_viewHolder.circleimageview);
    }

    @Override
    public int getItemCount() {
        return alluser.size();
    }


    public class Doctoritem_ViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView circleimageview;
        private TextView txtname;
        private TextView txttakhasos;
        private TextView txtdescriotion;
        private LinearLayout llbtndoctoritem;
        public Doctoritem_ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=(TextView)itemView.findViewById(R.id.txtname);
            txttakhasos=(TextView)itemView.findViewById(R.id.txttakhasos);
            txtdescriotion=(TextView)itemView.findViewById(R.id.txtdescription);
            circleimageview=(CircleImageView)itemView.findViewById(R.id.imgcircleuser);
            llbtndoctoritem=(LinearLayout)itemView.findViewById(R.id.btnadddoctoritem);
            llbtndoctoritem.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent i = new Intent(context, ListDoctorActivity.class);
                    i.putExtra("type",4);
                    i.putExtra("doctorid",alluser.get(getAdapterPosition()).getId());
                    context.startActivity(i);
                }
            });
        }

    }
}
