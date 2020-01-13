package com.example.salamatapp.AdaptorLayer;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.salamatapp.DomainLayer.City;
import com.example.salamatapp.DomainLayer.Group;
import com.example.salamatapp.ListDoctorActivity;
import com.example.salamatapp.ListGroupCityDoctorActivity;
import com.example.salamatapp.R;

import java.util.List;

public class CityItem_Adaptor extends RecyclerView.Adapter<CityItem_Adaptor.CityItem_ViewHolder> {


    private Context context;
    private List<City> allcity;

    public CityItem_Adaptor(Context context, List<City> allcity){
        this.context = context;
        this.allcity = allcity;
    }

    @NonNull
    @Override
    public CityItem_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_city_one_item,viewGroup,false);
        return new CityItem_Adaptor.CityItem_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityItem_ViewHolder cityItem_viewHolder, int i) {
        City city=allcity.get(i);
        cityItem_viewHolder.txtname.setText(city.getName());
    }

    @Override
    public int getItemCount() {
        return allcity.size();
    }



    public class CityItem_ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtname;
        private ImageView btncity;
        private LinearLayout btncityclick;
        public CityItem_ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=(TextView)itemView.findViewById(R.id.txtlistcityname);
            btncity=(ImageView) itemView.findViewById(R.id.imgnextcity);
            btncityclick=(LinearLayout)itemView.findViewById(R.id.btnlistcityclick);
            btncityclick.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent i = new Intent(context, ListGroupCityDoctorActivity.class);
                    i.putExtra("type",8);
                    i.putExtra("cityid",allcity.get(getAdapterPosition()).getId());
                    i.putExtra("cityname",allcity.get(getAdapterPosition()).getName());
                    context.startActivity(i);
                }
            });
        }

    }
}

