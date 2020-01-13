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

import com.example.salamatapp.DomainLayer.Group;
import com.example.salamatapp.DomainLayer.NoskheList;
import com.example.salamatapp.ListDoctorActivity;
import com.example.salamatapp.R;

import java.util.List;

public class NoskheFinaly_Adaptor extends RecyclerView.Adapter<NoskheFinaly_Adaptor.NoskheFinaly_ViewHolder> {


    private Context context;
    private List<NoskheList> allnoskhe;

    public NoskheFinaly_Adaptor(Context context, List<NoskheList> allnoskhe){
        this.context = context;
        this.allnoskhe = allnoskhe;
    }

    @NonNull
    @Override
    public NoskheFinaly_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_noskhe_finaly_layout,viewGroup,false);
        return new NoskheFinaly_Adaptor.NoskheFinaly_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoskheFinaly_ViewHolder noskheFinaly_viewHolder, int i) {

        NoskheList newnoskhe=allnoskhe.get(i);
        noskheFinaly_viewHolder.txtname.setText(newnoskhe.getName().toString());
        noskheFinaly_viewHolder.txtcount.setText(newnoskhe.getCount().toString());
        noskheFinaly_viewHolder.txtcounternoskhe.setText(i+1 +" ) ");
    }

    @Override
    public int getItemCount() {
        return allnoskhe.size();
    }


    public class NoskheFinaly_ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtname,txtcount,txtcounternoskhe;
        public NoskheFinaly_ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=(TextView)itemView.findViewById(R.id.txtnameofnoskhedrag);
            txtcount=(TextView) itemView.findViewById(R.id.txtcountofnoskhedrag);
            txtcounternoskhe=(TextView)itemView.findViewById(R.id.txtcounternoskhe);
        }

    }
}

