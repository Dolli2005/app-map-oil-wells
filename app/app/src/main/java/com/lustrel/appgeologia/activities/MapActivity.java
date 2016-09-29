package com.lustrel.appgeologia.activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.lustrel.appgeologia.R;
import com.lustrel.appgeologia.models.InternalDatabase;

import org.json.JSONArray;
import org.json.JSONObject;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private InternalDatabase internalDatabase;
    private GoogleMap map;
    private JSONArray databaseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;
        focusOnBrazil();
        loadDatabaseData();
        createMarkersOnMap();
    }

    private void focusOnBrazil(){
        LatLng brazilLocation = new LatLng(-13.1158448, -61.1816599);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(brazilLocation, 4));
    }

    private void loadDatabaseData(){
        internalDatabase = InternalDatabase.getInstance(this);
        databaseData = internalDatabase.getData();
    }

    private void createMarkersOnMap(){

    }
}
