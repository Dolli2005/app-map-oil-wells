package com.lustrel.appgeologia.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.lustrel.appgeologia.R;
import org.json.JSONException;
import org.json.JSONObject;

public class OilDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView lblOwner;
    private TextView lblLatitude;
    private TextView lblLongitude;
    private TextView lblState;
    private TextView lblBasin;
    private TextView lblField;
    private TextView lblLocation;
    private TextView lblDrillingYear;
    private TextView lblFinishDrilling;
    private TextView lblCategory;
    private TextView lblKind;
    private TextView lblReclassification;
    private TextView lblDepth;
    private TextView lblDrill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oil_details_activity);
        loadElementsFromXML();
        applyToolbar();
        populateDetails();
    }

    private void loadElementsFromXML(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        lblOwner = (TextView) findViewById(R.id.owner);
        lblLatitude = (TextView) findViewById(R.id.latitude);
        lblLongitude = (TextView) findViewById(R.id.longitude);
        lblState = (TextView) findViewById(R.id.state);
        lblBasin = (TextView) findViewById(R.id.basin);
        lblField = (TextView) findViewById(R.id.field);
        lblLocation = (TextView) findViewById(R.id.location);
        lblDrillingYear = (TextView) findViewById(R.id.drilling_year);
        lblFinishDrilling = (TextView) findViewById(R.id.finish_drilling);
        lblCategory = (TextView) findViewById(R.id.category);
        lblKind = (TextView) findViewById(R.id.kind);
        lblReclassification = (TextView) findViewById(R.id.reclassification);
        lblDepth = (TextView) findViewById(R.id.depth);
        lblDrill = (TextView) findViewById(R.id.drill);
    }

    private void applyToolbar(){
        setSupportActionBar(toolbar);
    }

    private void populateDetails(){
        try {
            String detailsAsText = getIntent().getStringExtra("MARKER_DETAILS");
            JSONObject details = new JSONObject(detailsAsText);

            setTitle(details.getString("name"));
            lblOwner.setText(getString(R.string.detail_owner_text) + " " + details.getString("operator"));
            lblLatitude.setText(getString(R.string.detail_latitude_text) + " " + details.getString("latitude"));
            lblLongitude.setText(getString(R.string.detail_longitude_text) + " " + details.getString("longitude"));
            lblState.setText(getString(R.string.detail_state_text) + " " + details.getString("state"));
            lblBasin.setText(getString(R.string.detail_basin_text) + " " + details.getString("basin"));
            lblField.setText(getString(R.string.detail_field_text) + " " + details.getString("field"));
            lblLocation.setText(getString(R.string.detail_location_text) + " " + details.getString("location"));
            lblDrillingYear.setText(getString(R.string.detail_drilling_year_text) + " " + details.getString("drillingYear"));
            lblFinishDrilling.setText(getString(R.string.detail_finish_drilling_text) + " " + details.getString("finishDrilling"));
            lblCategory.setText(getString(R.string.detail_category_text) + " " + details.getString("category"));
            lblKind.setText(getString(R.string.detail_kind_text) + " " + details.getString("kind"));
            lblReclassification.setText(getString(R.string.detail_reclassification_text) + " " + details.getString("reclassification"));
            lblDepth.setText(getString(R.string.detail_depth_text) + " " + details.getString("depth"));
            lblDrill.setText(getString(R.string.detail_drill_text) + " " + details.getString("drill"));
        } catch(JSONException exception){}

    }
}
