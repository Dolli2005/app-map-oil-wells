package com.lustrel.appgeologia.models;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;

public class InternalDatabase {

    private static InternalDatabase instance;
    private JSONArray data;

    private InternalDatabase(Context context){
        load(context);
    }

    public synchronized static InternalDatabase getInstance(Context context){
        if(instance == null){
            instance = new InternalDatabase(context);
        }
        return instance;
    }

    private void load(Context context){
        try {
            InputStream inputStream = context.getAssets().open("data.json");

            int contentSizeInBytes = inputStream.available();
            byte bufferInBytes[] = new byte[contentSizeInBytes];
            inputStream.read(bufferInBytes);
            inputStream.close();

            String jsonContentAsText = new String(bufferInBytes, "UTF-8");
            data = new JSONArray(jsonContentAsText);
        } catch(IOException exception){
            exception.printStackTrace();
        } catch(JSONException exception){
            exception.printStackTrace();
        }
    }

    public JSONArray getData(){
        return data;
    }
}
