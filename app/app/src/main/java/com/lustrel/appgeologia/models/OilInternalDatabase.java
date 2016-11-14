package com.lustrel.appgeologia.models;

import android.content.Context;

public class OilInternalDatabase extends InternalDatabase {

    private OilInternalDatabase(Context context){
        fileName = "oil-wells.json";
        load(context);
    }

    public synchronized static OilInternalDatabase getInstance(Context context){
        if(instance == null){
            instance = new OilInternalDatabase(context);
        }
        return (OilInternalDatabase)instance;
    }

}
