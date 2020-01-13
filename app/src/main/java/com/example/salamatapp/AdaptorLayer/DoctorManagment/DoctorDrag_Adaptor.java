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

import com.example.salamatapp.DoctorManagment.ManagmentDoctorDragActivity;
import com.example.salamatapp.DomainLayer.Doctor.DoctorDrag;
import com.example.salamatapp.DomainLayer.Doctor.DoctorVisit;
import com.example.salamatapp.ListDoctorActivity;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.DoctorDragApiService;

import java.util.List;

public class DoctorDrag_Adaptor extends RecyclerView.Adapter<DoctorDrag_Adaptor.DoctorDrag_ViewHolder> {


    private Context context;
    private List<DoctorDrag> alldrag;
    private DoctorDragApiService _doctordrag;
    private Intent i;

    public DoctorDrag_Adaptor(Context context, List<DoctorDrag> alldrag){
        this.context = context;
        this.alldrag = alldrag;
    }

    @NonNull
    @Override
    public DoctorDrag_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_doctor_drag_managment,viewGroup,false);
        return new DoctorDrag_Adaptor.DoctorDrag_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorDrag_ViewHolder doctorDrag_viewHolder, int i) {

        DoctorDrag drag=alldrag.get(i);
        doctorDrag_viewHolder.txtdes.setText(drag.getDescription());
        doctorDrag_viewHolder.txtname.setText(drag.getName());
    }

    @Override
    public int getItemCount() {
        return alldrag.size();
    }

    public class DoctorDrag_ViewHolder extends RecyclerView.ViewHolder{

        TextView txtname,txtdes;
        Button btndelete;
        public DoctorDrag_ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=(TextView)itemView.findViewById(R.id.txtnamedragmanagment);
            txtdes=(TextView)itemView.findViewById(R.id.txtdescriptiondragmanagment);

            btndelete=(Button)itemView.findViewById(R.id.btndeletedragmanagment);

            _doctordrag=new DoctorDragApiService(context);
            btndelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    _doctordrag.postdeletedrag(alldrag.get(getAdapterPosition()).getId(), new DoctorDragApiService.OnDoctorDragReceived() {
                        @Override
                        public void onReceived(String checkadddelete) {
                            i = new Intent(context, ManagmentDoctorDragActivity.class);
                            context.startActivity(i);
                            ((Activity)context).finish();
                        }
                    });
                }
            });



        }

    }
}


