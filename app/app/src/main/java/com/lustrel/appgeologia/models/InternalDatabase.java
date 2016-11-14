package com.lustrel.appgeologia.models;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

public abstract class InternalDatabase {

    static InternalDatabase instance;
    String fileName;
    protected JSONArray data;

    protected void load(Context context){
        try {
            InputStream inputStream = context.getAssets().open(fileName);

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
