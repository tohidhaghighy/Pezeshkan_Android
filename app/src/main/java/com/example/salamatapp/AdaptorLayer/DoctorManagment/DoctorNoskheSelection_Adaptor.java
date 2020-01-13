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
import android.widget.EditText;
import android.widget.TextView;

import com.example.salamatapp.AdaptorLayer.NoskheFinaly_Adaptor;
import com.example.salamatapp.DoctorManagment.ManagmentDoctorNoskheActivity;
import com.example.salamatapp.DomainLayer.Doctor.DoctorDrag;
import com.example.salamatapp.DomainLayer.NoskheList;
import com.example.salamatapp.ListDoctorActivity;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.NoskheApiService;

import java.util.List;

public class DoctorNoskheSelection_Adaptor extends RecyclerView.Adapter<DoctorNoskheSelection_Adaptor.DoctorNoskheSelection_ViewHolder> {


    private Context context;
    private List<DoctorDrag> allnoskhe;
    private NoskheApiService _noskheapi;
    private Integer visitid;

    public DoctorNoskheSelection_Adaptor(Context context, List<DoctorDrag> allnoskhe,Integer visitid){
        this.context = context;
        this.allnoskhe = allnoskhe;
        this.visitid=visitid;
    }

    @NonNull
    @Override
    public DoctorNoskheSelection_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_noskhe_selection_layout,viewGroup,false);
        return new DoctorNoskheSelection_Adaptor.DoctorNoskheSelection_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorNoskheSelection_ViewHolder doctorNoskheSelection_viewHolder, int i) {

        DoctorDrag noskhe=allnoskhe.get(i);
        doctorNoskheSelection_viewHolder.txtname.setText(noskhe.getName().toString());
    }

    @Override
    public int getItemCount() {
        return allnoskhe.size();
    }


    public class DoctorNoskheSelection_ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtname;
        private EditText tdtcount;
        private Button btnadddrag;
        public DoctorNoskheSelection_ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=(TextView)itemView.findViewById(R.id.txtnameofsearchofnoskhe);
            tdtcount=(EditText) itemView.findViewById(R.id.txtcountofsearchofnoskhe);
            btnadddrag=(Button)itemView.findViewById(R.id.btnselectdragtofinalylist);
            btnadddrag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    _noskheapi=new NoskheApiService(context);
                    _noskheapi.postaddmedicinetonoskhe(txtname.getText().toString(), Integer.parseInt(tdtcount.getText().toString()), visitid, new NoskheApiService.OnReturnvalueReceived() {
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

