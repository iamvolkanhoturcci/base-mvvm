package com.volkanhotur.basemvvm.android.utils;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.Map;
import java.util.Set;

import io.reactivex.annotations.Nullable;

@SuppressWarnings({"JavaDoc", "WeakerAccess"})
public class SharedHelper {

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public SharedHelper(SharedPreferences sharedPreferences, Gson gson) {
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }

    /**
     * Add given string to shared preferences
     * @param key
     * @param value
     */
    public void addString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Retrieve string with given key from shared preferences
     * @param key
     * @param defaultValue
     * @return
     */
    public String retrieveString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    /**
     * Add given int to shared preferences
     * @param key
     * @param value
     */
    public void addInt(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * Retrieve int with given key from shared preferences
     * @param key
     * @param defaultValue
     * @return
     */
    public int retrieveInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    /**
     * Add float with given key to shared preferences
     * @param key
     * @param value
     */
    public void addFloat(String key, float value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    /**
     * Retrieve float with given key from shared preferences
     * @param key
     * @param defaultValue
     * @return
     */
    public float retrievefloat(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    /**
     * Add long with given key to Shared Preferences
     * @param key
     * @param value
     */
    public void addLong(String key, long value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    /**
     * Retrieve long with given key from shared preferences
     * @param key
     * @param defaultValue
     * @return
     */
    public long retrieveLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    /**
     * Add boolean with given key to shared preferences
     * @param key
     * @param value
     */
    public void addBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Retrieve boolean with given key from shared preferences
     * @param key
     * @param defaultValue
     * @return
     */
    public boolean retrieveBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    /**
     * Add String set with given key to Shared preferences
     * @param key
     * @param value
     */
    public void addStringSet(String key, Set<String> value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(key, value);
        editor.apply();
    }

    /**
     * Retrieve String set with given key from shared preferences
     * @param key
     * @param defaultValue
     * @return
     */
    public Set<String> retrieveStringSet(String key, Set<String> defaultValue) {
        return sharedPreferences.getStringSet(key, defaultValue);
    }

    /**
     * Add object data with given key to Shared preferences
     * @param key
     * @param data
     * @param <T>
     */
    public <T> void addData(String key, T data) {

        if (data == null)
            throw new RuntimeException("Data can not be null");

        String json = gson.toJson(data);

        if (json != null)
            addString(key, json);
        else
            throw new RuntimeException("Data can not be null");
    }

    /**
     * Retrieve Object model with given key from Shared Preferences
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    @Nullable
    public <T> T retrieveData(String key, Class<T> clazz) {
        if (sharedPreferences.contains(key)) {
            String json = retrieveString(key, null);

            if (json == null)
                return null;

            return gson.fromJson(json, clazz);

        } else {
            return null;
        }
    }

    /**
     * Fetch all data from Shared Preferences
     * @return
     */
    public Map<String, ?> fetchAll() {
        return sharedPreferences.getAll();
    }

    /**
     * Check shared preferences contains given key
     * @param key
     * @return
     */
    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    /**
     * Remove item from Shared preferences with given key
     * @param key
     */
    public void removeItem(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * Clear all shared preferences
     */
    public void clear() {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();
    }

}