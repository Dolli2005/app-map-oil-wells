package com.lustrel.geoaccess.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.lustrel.geoaccess.R;

import org.json.JSONException;
import org.json.JSONObject;

public class WaterDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView lblLatitude;
    private TextView lblLongitude;
    private TextView lblState;
    private TextView lblLocation;
    private TextView lblCounty;
    private TextView lblDepth;
    private TextView lblStaticLevel;
    private TextView lblDynamicLevel;
    private TextView lblFlowNumber;
    private TextView lblPresentSolids;
    private TextView lblFlowRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.water_details_activity);
        loadElementsFromXML();
        applyToolbar();
        populateDetails();
    }

    private void loadElementsFromXML(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        lblLatitude = (TextView) findViewById(R.id.latitude);
        lblLongitude = (TextView) findViewById(R.id.longitude);
        lblState = (TextView) findViewById(R.id.state);
        lblLocation = (TextView) findViewById(R.id.location);
        lblCounty = (TextView) findViewById(R.id.county);
        lblDepth = (TextView) findViewById(R.id.depth);
        lblStaticLevel = (TextView) findViewById(R.id.static_level);
        lblDynamicLevel = (TextView) findViewById(R.id.dynamic_level);
        lblFlowNumber = (TextView) findViewById(R.id.flow_number);
        lblPresentSolids = (TextView) findViewById(R.id.solids_present);
        lblFlowRate = (TextView) findViewById(R.id.flow_rate);
    }

    private void applyToolbar(){
        setSupportActionBar(toolbar);
    }

    private void populateDetails(){
        try {
            String detailsAsText = getIntent().getStringExtra("MARKER_DETAILS");
            JSONObject details = new JSONObject(detailsAsText);

            setTitle(details.getString("name"));
            if(details.has("latitude"))
                lblLatitude.setText(getString(R.string.detail_latitude_text) + " " + details.getString("latitude"));
            if(details.has("longitude"))
                lblLongitude.setText(getString(R.string.detail_longitude_text) + " " + details.getString("longitude"));
            if(details.has("state"))
                lblState.setText(getString(R.string.detail_state_text) + " " + details.getString("state"));
            if(details.has("location"))
                lblLocation.setText(getString(R.string.detail_location_text) + " " + details.getString("location"));
            if(details.has("county"))
                lblCounty.setText(getString(R.string.detail_county_text) + " " + details.getString("county"));
            if(details.has("depth"))
                lblDepth.setText(getString(R.string.detail_depth_text) + " " + details.getString("depth"));
            if(details.has("nivelestatico"))
                lblStaticLevel.setText(getString(R.string.detail_static_level_text) + " " + details.getString("nivelestatico"));
            if(details.has("niveldinamico"))
                lblDynamicLevel.setText(getString(R.string.detail_dynamic_level_text) + " " + details.getString("niveldinamico"));
            if(details.has("numerodavazao"))
                lblFlowNumber.setText(getString(R.string.detail_flow_number_text) + " " + details.getString("numerodavazao"));
            if(details.has("solidspresent"))
                lblPresentSolids.setText(getString(R.string.detail_present_solids_text) + " " + details.getString("solidspresent"));
            if(details.has("flowrate"))
                lblFlowRate.setText(getString(R.string.detail_flow_rate_text) + " " + details.getString("flowrate"));
        } catch(JSONException exception){
            Log.e("H", "H");
            Toast.makeText(this, "What", Toast.LENGTH_SHORT).show();
        }
    }
}
