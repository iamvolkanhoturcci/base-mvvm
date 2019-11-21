package com.volkanhotur.basemvvm.android.utils;

import android.util.Base64;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public final class EncodingUtils {

    public static String getBase64String(Map<String, Object> request){
        Gson gson = new Gson();
        String jsonString = gson.toJsonTree(request).getAsJsonObject().toString();

        return Base64.encodeToString(jsonString.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
    }

    public static String getJSONFromBase64String(String data){
        byte[] decodedString = Base64.decode(data, Base64.DEFAULT);

        return new String(decodedString, StandardCharsets.UTF_8);
    }

    public static String getBase64String(JSONObject object){
        return Base64.encodeToString(object.toString().getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
    }

    public static String getBase64String(String data){
        return Base64.encodeToString(data.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
    }
}
