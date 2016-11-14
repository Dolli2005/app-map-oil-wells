package com.lustrel.appgeologia.models;

import android.content.Context;

public class WaterInternalDatabase extends InternalDatabase {

    static WaterInternalDatabase instance;

    private WaterInternalDatabase(Context context){
        fileName = "water-wells.json";
        load(context);
    }

    public synchronized static WaterInternalDatabase getInstance(Context context){
        if(instance == null){
            instance = new WaterInternalDatabase(context);
        }
        return instance;
    }
}
