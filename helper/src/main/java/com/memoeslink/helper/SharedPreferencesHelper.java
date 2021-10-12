package com.memoeslink.helper;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class SharedPreferencesHelper extends ContextWrapper {
    public static final String PREFERENCES = "prefs";
    private static final Boolean DEFAULT_BOOLEAN = false;
    private static final Float DEFAULT_FLOAT = 0F;
    private static final Integer DEFAULT_INTEGER = 0;
    private static final Long DEFAULT_LONG = 0L;
    private static final String DEFAULT_STRING = "";
    private static final Set<String> DEFAULT_STRING_SET = Collections.emptySet();
    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;

    public SharedPreferencesHelper(Context context) {
        super(context);
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
    }

    public SharedPreferencesHelper(Context context, String name) {
        super(context);
        preferences = getSharedPreferences(name, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static SharedPreferencesHelper getPreferencesHelper(Context context) {
        return new SharedPreferencesHelper(context, PREFERENCES);
    }

    public Boolean getBoolean(String key) {
        return preferences.getBoolean(key, DEFAULT_BOOLEAN);
    }

    public Boolean getBoolean(String key, boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    public Boolean getBooleanOrNull(String key) {
        return preferences.contains(key) ? getBoolean(key) : null;
    }

    public boolean putBoolean(String key, boolean value) {
        return editor.putBoolean(key, value).commit();
    }

    public void putBooleanSafely(String key, boolean value) {
        editor.putBoolean(key, value).apply();
    }

    public Float getFloat(String key) {
        return preferences.getFloat(key, DEFAULT_FLOAT);
    }

    public Float getFloat(String key, float defaultValue) {
        return preferences.getFloat(key, defaultValue);
    }

    public Float getFloatOrNull(String key) {
        return preferences.contains(key) ? getFloat(key) : null;
    }

    public boolean putFloat(String key, float value) {
        return editor.putFloat(key, value).commit();
    }

    public void putFloatSafely(String key, float value) {
        editor.putFloat(key, value).apply();
    }

    public Integer getInt(String key) {
        return preferences.getInt(key, DEFAULT_INTEGER);
    }

    public Integer getInt(String key, int defaultValue) {
        return preferences.getInt(key, defaultValue);
    }

    public Integer getIntOrNull(String key) {
        return preferences.contains(key) ? getInt(key) : null;
    }

    public boolean putInt(String key, int value) {
        return editor.putInt(key, value).commit();
    }

    public void putIntSafely(String key, int value) {
        editor.putInt(key, value).apply();
    }

    public Long getLong(String key) {
        return preferences.getLong(key, DEFAULT_LONG);
    }

    public Long getLong(String key, long defaultValue) {
        return preferences.getLong(key, defaultValue);
    }

    public Long getLongOrNull(String key) {
        return preferences.contains(key) ? getLong(key) : null;
    }

    public boolean putLong(String key, long value) {
        return editor.putLong(key, value).commit();
    }

    public void putLongSafely(String key, long value) {
        editor.putLong(key, value).apply();
    }

    public String getString(String key) {
        return preferences.getString(key, DEFAULT_STRING);
    }

    public String getString(String key, String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    public String getStringOrNull(String key) {
        return preferences.contains(key) ? getString(key) : null;
    }

    public int getStringAsInt(String key) {
        return getStringAsInt(key, DEFAULT_INTEGER);
    }

    public int getStringAsInt(String key, int defaultValue) {
        if (key == null)
            return defaultValue;

        try {
            return Integer.parseInt(getString(key, DEFAULT_STRING));
        } catch (NumberFormatException ignored) {
            return defaultValue;
        }
    }

    public boolean putString(String key, String value) {
        return editor.putString(key, value).commit();
    }

    public void putStringSafely(String key, String value) {
        editor.putString(key, value).apply();
    }

    public Set<String> getStringSet(String key) {
        return preferences.getStringSet(key, DEFAULT_STRING_SET);
    }

    public Set<String> getStringSet(String key, Set<String> defaultValue) {
        return preferences.getStringSet(key, defaultValue);
    }

    public Set<String> getStringSetOrNull(String key) {
        return preferences.contains(key) ? getStringSet(key) : null;
    }

    public boolean putStringSet(String key, Set<String> value) {
        return editor.putStringSet(key, value).commit();
    }

    public void putStringSetSafely(String key, Set<String> value) {
        editor.putStringSet(key, value).apply();
    }

    public Map<String, ?> getAll(String key) {
        return preferences.getAll();
    }

    public boolean contains(String key) {
        return preferences.contains(key);
    }

    public boolean remove(String key) {
        SharedPreferences.Editor editor = preferences.edit();

        if (preferences.contains(key))
            return editor.remove(key).commit();
        return false;
    }

    public boolean clear() {
        SharedPreferences.Editor editor = preferences.edit();
        return editor.clear().commit();
    }

    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        preferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        preferences.unregisterOnSharedPreferenceChangeListener(listener);
    }
}
