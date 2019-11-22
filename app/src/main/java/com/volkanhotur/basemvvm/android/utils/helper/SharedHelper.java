package com.volkanhotur.basemvvm.android.utils.helper;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.Map;
import java.util.Set;

import io.reactivex.annotations.Nullable;

/**
 * @author volkanhotur
 */

public class SharedHelper {

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public SharedHelper(SharedPreferences sharedPreferences, Gson gson) {
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }

    private void addString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    private String retrieveString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public void addInt(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int retrieveInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public void addFloat(String key, float value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public float retrieveFloat(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public void addLong(String key, long value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public long retrieveLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    public void addBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean retrieveBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public void addStringSet(String key, Set<String> value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(key, value);
        editor.apply();
    }

    public Set<String> retrieveStringSet(String key, Set<String> defaultValue) {
        return sharedPreferences.getStringSet(key, defaultValue);
    }

    public <T> void addData(String key, T data) throws RuntimeException {

        if (data == null) {
            throw new RuntimeException("Data can not be null");
        }

        String json = gson.toJson(data);

        if (json != null) {
            addString(key, json);
        } else {
            throw new RuntimeException("Data can not be null");
        }
    }

    @Nullable
    public <T> T retrieveData(String key, Class<T> clazz) {
        if (sharedPreferences.contains(key)) {
            String json = retrieveString(key, null);

            if (json == null) {
                return null;
            }

            return gson.fromJson(json, clazz);

        } else {
            return null;
        }
    }

    public Map<String, ?> fetchAll() {
        return sharedPreferences.getAll();
    }

    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    public void removeItem(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public void clear() {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();
    }

}