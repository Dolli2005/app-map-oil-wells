package com.lustrel.appgeologia.activities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Window;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.lustrel.appgeologia.R;
import com.lustrel.appgeologia.models.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private OilInternalDatabase oilInternalDatabase;
    private WaterInternalDatabase waterInternalDatabase;
    private GoogleMap map;
    private JSONArray oilWellsData;
    private JSONArray waterWellsData;
    private JSONObject lastClickedMarkerDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.map_activity);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;
        focusOnBrazil();
        applyCustomStyle();

        loadOilDatabaseData();
        createOilMarkersOnMap();

        loadWaterDatabaseData();
        createWaterMarkersOnMap();

        map.setOnMarkerClickListener(new MarkerClickHandler());
    }

    private void focusOnBrazil(){
        LatLng brazilLocation = new LatLng(-13.1158448, -61.1816599);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(brazilLocation, 3));
    }

    private void applyCustomStyle(){
        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style);
        map.setMapStyle(style);
    }

    private void loadOilDatabaseData(){
        oilInternalDatabase = OilInternalDatabase.getInstance(this);
        oilWellsData = oilInternalDatabase.getData();
    }

    private void createOilMarkersOnMap(){
        try {
            for(int i = 0; i < oilWellsData.length(); i++){
                JSONObject place = oilWellsData.getJSONObject(i);

                Double placeLatitude = place.getDouble("latitude");
                Double placeLongitude = place.getDouble("longitude");
                LatLng placeLocation = new LatLng(placeLatitude, placeLongitude);

                MarkerOptions markerOptions = new MarkerOptions()
                        .title("oil")
                        //.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_oil_marker))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.oil_marker))
                        .snippet("" + i)
                        .position(placeLocation);

                map.addMarker(markerOptions);
            }
        } catch(JSONException exception){}
    }

    private void loadWaterDatabaseData(){
        waterInternalDatabase = WaterInternalDatabase.getInstance(this);
        waterWellsData = waterInternalDatabase.getData();
    }

    private void createWaterMarkersOnMap(){
        try {
            for(int i = 0; i < waterWellsData.length(); i++){
                JSONObject place = waterWellsData.getJSONObject(i);

                Double placeLatitude = place.getDouble("latitude");
                Double placeLongitude = place.getDouble("longitude");
                LatLng placeLocation = new LatLng(placeLatitude, placeLongitude);

                MarkerOptions markerOptions = new MarkerOptions()
                        .title("water")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.water_marker))
                        .snippet("" + i)
                        .position(placeLocation);

                map.addMarker(markerOptions);
            }
        } catch(JSONException exception){}
    }

    private void openOilDetailsActivity(){
        Intent intent = new Intent(MapActivity.this, OilDetailsActivity.class);
        intent.putExtra("MARKER_DETAILS", lastClickedMarkerDetails.toString());
        startActivity(intent);
    }

    private void openWaterDetailsActivity(){
        Intent intent = new Intent(MapActivity.this, WaterDetailsActivity.class);
        intent.putExtra("MARKER_DETAILS", lastClickedMarkerDetails.toString());
        startActivity(intent);
    }

    public class MarkerClickHandler implements GoogleMap.OnMarkerClickListener {
        @Override
        public boolean onMarkerClick(Marker marker){
            try {
                String clickedMarkerType = marker.getTitle();
                int markerIndexOnData = Integer.parseInt(marker.getSnippet());

                if(clickedMarkerType.equals("oil")) {
                    lastClickedMarkerDetails = oilWellsData.getJSONObject(markerIndexOnData);
                    openOilDetailsActivity();
                } else if(clickedMarkerType.equals("water")){
                    lastClickedMarkerDetails = waterWellsData.getJSONObject(markerIndexOnData);
                    openWaterDetailsActivity();
                }
            } catch(JSONException ex){}

            return true;
        }
    }
}
