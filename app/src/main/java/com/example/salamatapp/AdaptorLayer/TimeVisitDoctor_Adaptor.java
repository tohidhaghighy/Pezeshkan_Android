package com.example.salamatapp.AdaptorLayer;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salamatapp.DomainLayer.City;
import com.example.salamatapp.DomainLayer.VisitTimeDoctor;
import com.example.salamatapp.ListDoctorActivity;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.NoskheApiService;
import com.example.salamatapp.ServiceLayer.ReserveApiService;

import java.util.List;

public class TimeVisitDoctor_Adaptor extends RecyclerView.Adapter<TimeVisitDoctor_Adaptor.TimeVisitDoctor_ViewHolder> {


    private Context context;
    private List<VisitTimeDoctor> allVisitTimeDoctor;
    private ReserveApiService _rezervapi;
    private String number;
    private Integer doctorid;

    public TimeVisitDoctor_Adaptor(Context context, List<VisitTimeDoctor> allVisitTimeDoctor,String number,Integer doctorid){
        this.context = context;
        this.allVisitTimeDoctor = allVisitTimeDoctor;
        this.number=number;
        this.doctorid=doctorid;
    }

    @NonNull
    @Override
    public TimeVisitDoctor_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_timevisit_one_item,viewGroup,false);
        return new TimeVisitDoctor_Adaptor.TimeVisitDoctor_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeVisitDoctor_ViewHolder timeVisitDoctor_viewHolder, int i) {

        VisitTimeDoctor visittime=allVisitTimeDoctor.get(i);
        timeVisitDoctor_viewHolder.txtdate.setText(visittime.getDate());
        timeVisitDoctor_viewHolder.txtdes.setText(visittime.getDescription());
    }

    @Override
    public int getItemCount() {
        return allVisitTimeDoctor.size();
    }


    public class TimeVisitDoctor_ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtdate;
        private TextView txtdes;
        private Button btnaddvisit;
        public TimeVisitDoctor_ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtdate=(TextView)itemView.findViewById(R.id.txtdatevisit);
            txtdes=(TextView)itemView.findViewById(R.id.txtdesvicit);
            btnaddvisit=(Button) itemView.findViewById(R.id.btnaddvisittime);
            btnaddvisit.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {

                    _rezervapi=new ReserveApiService(context);
                    _rezervapi.postaddreservedoctor(number, allVisitTimeDoctor.get(getAdapterPosition()).getId(), doctorid, allVisitTimeDoctor.get(getAdapterPosition()).getDate(), new NoskheApiService.OnReturnvalueReceived() {
                        @Override
                        public void onReceived(String checkvalue) {
                            Log.e("tags",number);
                            Log.e("tagss",allVisitTimeDoctor.get(getAdapterPosition()).getId()+"");
                            Log.e("tagsss",doctorid+"");
                            Log.e("date",allVisitTimeDoctor.get(getAdapterPosition()).getDate().toString());
                            Toast.makeText(context, "با موفقیت رزرو شد ",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
        }

    }
}

