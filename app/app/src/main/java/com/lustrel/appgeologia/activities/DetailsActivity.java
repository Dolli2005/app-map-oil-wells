package com.lustrel.appgeologia.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.lustrel.appgeologia.R;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailsActivity extends Activity {

    private TextView lblName;
    private TextView lblDepth;
    private TextView lblBasin;
    private TextView lblOwner;
    private TextView lblDrillingYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        getAllViewElements();
        populateDetails();
    }

    private void getAllViewElements(){
        lblName = (TextView) findViewById(R.id.name);
        lblDepth = (TextView) findViewById(R.id.depth);
        lblBasin = (TextView) findViewById(R.id.basin);
        lblOwner = (TextView) findViewById(R.id.owner);
        lblDrillingYear = (TextView) findViewById(R.id.drilling_year);
    }

    private void populateDetails(){
        try {
            String detailsAsText = getIntent().getStringExtra("MARKER_DETAILS");
            JSONObject details = new JSONObject(detailsAsText);

            lblName.setText(details.getString("name"));
            lblDepth.setText("Profundidade: " + details.getString("depth"));
            lblBasin.setText("Bacia: " + details.getString("basin"));
            lblOwner.setText("Proprietário: " + details.getString("owner"));
            lblDrillingYear.setText("Ano de perfuração: " + details.getString("drillingYear"));
        } catch(JSONException exception){}

    }
}
