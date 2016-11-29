package com.lustrel.geoaccess.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.lustrel.geoaccess.R;

import org.json.JSONException;
import org.json.JSONObject;

public class WaterDetailsActivity extends Activity {

    private TextView lblName;
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

        getAllViewElements();
        populateDetails();
    }

    private void getAllViewElements(){
        /*
        lblName = (TextView) findViewById(R.id.name);
        lblLatitude = (TextView) findViewById(R.id.latitude);
        lblLongitude = (TextView) findViewById(R.id.longitude);
        lblState = (TextView) findViewById(R.id.state);
        lblLocation = (TextView) findViewById(R.id.location);
        lblCounty = (TextView) findViewById(R.id.county);
        lblDepth = (TextView) findViewById(R.id.depth);
        lblStaticLevel = (TextView) findViewById(R.id.static_level);
        lblDynamicLevel = (TextView) findViewById(R.id.dynamic_level);
        lblFlowNumber = (TextView) findViewById(R.id.flow_number);
        lblPresentSolids = (TextView) findViewById(R.id.present_solids);
        lblFlowRate = (TextView) findViewById(R.id.flow_rate);
        */
    }

    private void populateDetails(){
        /*
        try {
            String detailsAsText = getIntent().getStringExtra("MARKER_DETAILS");
            JSONObject details = new JSONObject(detailsAsText);

            lblName.setText(details.getString("name"));
            lblLatitude.setText(getString(R.string.detail_latitude_text) + " " + details.getString("latitude"));
            lblLongitude.setText(getString(R.string.detail_longitude_text) + " " + details.getString("longitude"));
            lblState.setText(getString(R.string.detail_state_text) + " " + details.getString("state"));
            lblLocation.setText(getString(R.string.detail_location_text) + " " + details.getString("location"));
            lblCounty.setText(getString(R.string.detail_county_text) + " " + details.getString("county"));
            lblDepth.setText(getString(R.string.detail_depth_text) + " " + details.getString("depth"));
            lblStaticLevel.setText(getString(R.string.detail_static_level_text) + " " + details.getString("nivelestatico"));
            lblDynamicLevel.setText(getString(R.string.detail_dynamic_level_text) + " " + details.getString("niveldinamico"));
            lblFlowNumber.setText(getString(R.string.detail_flow_number_text) + " " + details.getString("numerodavazao"));
            lblPresentSolids.setText(getString(R.string.detail_present_solids_text) + " " + details.getString("solidspresent"));
            lblFlowRate.setText(getString(R.string.detail_flow_rate_text) + " " + details.getString("flowrate"));
        } catch(JSONException exception){}
        */
    }
}
