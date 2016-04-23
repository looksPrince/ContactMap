package com.android.contactmap.ui;

import android.os.Bundle;
import android.util.Log;

import com.android.contactmap.data.ContactData;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private ArrayList<LatLng> coordinates;
    private ArrayList<ContactData> data;
    private MarkerOptions mMarkerOptions;

    MainActivity activity;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getMapAsync(this);
        activity = ((MainActivity)getActivity());
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMarkerOptions = new MarkerOptions();
        mMap.setOnMarkerClickListener(this);

        boolean condition = true;
        while (condition){
            if(activity.dataReceived==1){
                condition= false;
                updateList(activity.getList());
            }else if(activity.dataReceived==0){
                condition= false;
            }
        }

    }

    public void updateList(ArrayList<ContactData> data){
        this.data = data;
        coordinates = new ArrayList<>();
        for(int i=0;i<data.size();i++){
            LatLng latLng = new LatLng(data.get(i).getLatitude(),data.get(i).getLongitude());
            coordinates.add(latLng);
            mMap.addMarker(mMarkerOptions.position(coordinates.get(i)).title(data.get(i).getName()));
        }

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.d("Map",marker.getTitle());
        return false;
    }
}
