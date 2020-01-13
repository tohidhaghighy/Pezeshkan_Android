package com.example.salamatapp.AdaptorLayer.DoctorManagment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.salamatapp.AdaptorLayer.NoskheFinaly_Adaptor;
import com.example.salamatapp.DoctorManagment.ManagmentDoctorNoskheActivity;
import com.example.salamatapp.DomainLayer.NoskheList;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.NoskheApiService;

import java.util.List;

public class DoctorNoskheWithDelete_Adaptor extends RecyclerView.Adapter<DoctorNoskheWithDelete_Adaptor.DoctorNoskheWithDelete_ViewHolder> {


    private Context context;
    private List<NoskheList> allnoskhe;
    private NoskheApiService _noskheapiservice;
    private Integer visitid;

    public DoctorNoskheWithDelete_Adaptor(Context context, List<NoskheList> allnoskhe,Integer visitid){
        this.context = context;
        this.allnoskhe = allnoskhe;
        this.visitid=visitid;
    }

    @NonNull
    @Override
    public DoctorNoskheWithDelete_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_noskhe_finally_withdelete_layout,viewGroup,false);
        return new DoctorNoskheWithDelete_Adaptor.DoctorNoskheWithDelete_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorNoskheWithDelete_ViewHolder doctorNoskheWithDelete_viewHolder, int i) {
        NoskheList newnoskhe=allnoskhe.get(i);
        doctorNoskheWithDelete_viewHolder.txtname.setText(newnoskhe.getName().toString());
        doctorNoskheWithDelete_viewHolder.txtcount.setText(newnoskhe.getCount().toString());
    }

    @Override
    public int getItemCount() {
        return allnoskhe.size();
    }


    public class DoctorNoskheWithDelete_ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtname,txtcount;
        private Button btndelete;
        public DoctorNoskheWithDelete_ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=(TextView)itemView.findViewById(R.id.txtnameofnoskhedrag);
            txtcount=(TextView) itemView.findViewById(R.id.txtcountofnoskhedrag);
            btndelete=(Button)itemView.findViewById(R.id.btndeletedragselectionnoskhe);
            _noskheapiservice=new NoskheApiService(context);
            btndelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    _noskheapiservice.postdeletemedicinefromnoskhe(allnoskhe.get(getAdapterPosition()).getId(), new NoskheApiService.OnReturnvalueReceived() {
                        @Override
                        public void onReceived(String checkvalue) {
                            Intent i = new Intent(context, ManagmentDoctorNoskheActivity.class);
                            i.putExtra("visitid",visitid);
                            context.startActivity(i);
                            ((Activity)context).finish();
                        }
                    });
                }
            });
        }

    }
}

