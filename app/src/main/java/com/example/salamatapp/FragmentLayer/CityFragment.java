package com.example.salamatapp.FragmentLayer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.salamatapp.AdaptorLayer.CityItem_Adaptor;
import com.example.salamatapp.DomainLayer.City;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.CityApiService;
import com.example.salamatapp.ServiceLayer.DataFakeGenarator;

import java.util.List;

public class CityFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_list_city,container,false);
        CityApiService _city=new CityApiService(view.getContext());
        _city.getCities(new CityApiService.OnCitiesReceived() {
            @Override
            public void onReceived(List<City> cities) {
                RecyclerView rv=(RecyclerView)view.findViewById(R.id.cityrecycleview);
                CityItem_Adaptor city=new CityItem_Adaptor(view.getContext(), cities);
                rv.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
                rv.setAdapter(city);
            }
        });

        return view;
    }
}