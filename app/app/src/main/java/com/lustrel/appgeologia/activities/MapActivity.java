package com.lustrel.appgeologia.activities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Window;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.lustrel.appgeologia.R;
import com.lustrel.appgeologia.models.InternalDatabase;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private InternalDatabase internalDatabase;
    private GoogleMap map;
    private JSONArray databaseData;
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
        loadDatabaseData();
        createMarkersOnMap();
        map.setOnMarkerClickListener(new MarkerClickHandler());
    }

    private void focusOnBrazil(){
        LatLng brazilLocation = new LatLng(-13.1158448, -61.1816599);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(brazilLocation, 3));
    }

    private void loadDatabaseData(){
        internalDatabase = InternalDatabase.getInstance(this);
        databaseData = internalDatabase.getData();
    }

    private void createMarkersOnMap(){
        try {
            for(int i = 0; i < databaseData.length(); i++){
                JSONObject place = databaseData.getJSONObject(i);

                String placeName = place.getString("name");
                Double placeLatitude = place.getDouble("latitude");
                Double placeLongitude = place.getDouble("longitude");
                LatLng placeLocation = new LatLng(placeLatitude, placeLongitude);

                MarkerOptions markerOptions = new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_oil_marker))
                        .title(placeName)
                        .snippet("" + i)
                        .position(placeLocation);

                map.addMarker(markerOptions);
            }
        } catch(JSONException exception){}
    }

    private void openDetailsActivity(){
        Intent intent = new Intent(MapActivity.this, DetailsActivity.class);
        intent.putExtra("MARKER_DETAILS", lastClickedMarkerDetails.toString());
        startActivity(intent);
    }

    public class MarkerClickHandler implements GoogleMap.OnMarkerClickListener {
        @Override
        public boolean onMarkerClick(Marker marker){
            try {
                int markerIndexOnData = Integer.parseInt(marker.getSnippet());
                lastClickedMarkerDetails = databaseData.getJSONObject(markerIndexOnData);
                openDetailsActivity();
            } catch(JSONException ex){}

            return true;
        }
    }
}
