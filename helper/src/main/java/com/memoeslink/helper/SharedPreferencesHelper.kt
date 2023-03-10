package com.memoeslink.helper

import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import androidx.preference.PreferenceManager

open class SharedPreferencesHelper : ContextWrapper {
    private val preferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    constructor(context: Context?) : super(context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context!!)
        editor = preferences.edit()
    }

    constructor(context: Context?, name: String?) : super(context) {
        preferences = getSharedPreferences(name, MODE_PRIVATE)
        editor = preferences.edit()
    }

    open fun getBoolean(key: String?): Boolean = preferences.getBoolean(key, DEFAULT_BOOLEAN)

    open fun getBoolean(key: String?, defaultValue: Boolean): Boolean =
        preferences.getBoolean(key, defaultValue)

    open fun getBooleanOrNull(key: String?): Boolean? = if (contains(key)) getBoolean(key) else null

    open fun put(key: String?, value: Boolean): Boolean = putBoolean(key, value)

    open fun putBoolean(key: String?, value: Boolean): Boolean =
        editor.putBoolean(key, value).commit()

    open fun save(key: String?, value: Boolean) = saveBoolean(key, value)

    open fun saveBoolean(key: String?, value: Boolean) = editor.putBoolean(key, value).apply()

    open fun getFloat(key: String?): Float = preferences.getFloat(key, DEFAULT_FLOAT)

    open fun getFloat(key: String?, defaultValue: Float): Float =
        preferences.getFloat(key, defaultValue)

    open fun getFloatOrNull(key: String?): Float? = if (contains(key)) getFloat(key) else null

    open fun put(key: String?, value: Float): Boolean = putFloat(key, value)

    open fun putFloat(key: String?, value: Float): Boolean = editor.putFloat(key, value).commit()

    open fun save(key: String?, value: Float) = saveFloat(key, value)

    open fun saveFloat(key: String?, value: Float) = editor.putFloat(key, value).apply()

    open fun getInt(key: String?): Int = preferences.getInt(key, DEFAULT_INTEGER)

    open fun getInt(key: String?, defaultValue: Int): Int = preferences.getInt(key, defaultValue)

    open fun getIntOrNull(key: String?): Int? = if (contains(key)) getInt(key) else null

    open fun put(key: String?, value: Int): Boolean = putInt(key, value)

    open fun putInt(key: String?, value: Int): Boolean = editor.putInt(key, value).commit()

    open fun save(key: String?, value: Int) = saveInt(key, value)

    open fun saveInt(key: String?, value: Int) = editor.putInt(key, value).apply()

    open fun getLong(key: String?): Long = preferences.getLong(key, DEFAULT_LONG)

    open fun getLong(key: String?, defaultValue: Long): Long =
        preferences.getLong(key, defaultValue)

    open fun getLongOrNull(key: String?): Long? = if (contains(key)) getLong(key) else null

    open fun put(key: String?, value: Long): Boolean = putLong(key, value)

    open fun putLong(key: String?, value: Long): Boolean = editor.putLong(key, value).commit()

    open fun save(key: String?, value: Long) = saveLong(key, value)

    open fun saveLong(key: String?, value: Long) = editor.putLong(key, value).apply()

    open fun getString(key: String?): String? = preferences.getString(key, DEFAULT_STRING)

    open fun getString(key: String?, defaultValue: String?): String? =
        preferences.getString(key, defaultValue)

    open fun getStringOrNull(key: String?): String? = if (contains(key)) getString(key) else null

    open fun getStringAsInt(key: String?): Int = getStringAsInt(key, DEFAULT_INTEGER)

    open fun getStringAsInt(key: String?, defaultValue: Int): Int {
        getString(key)?.let { s ->
            try {
                return Integer.parseInt(s)
            } catch (e: NumberFormatException) {
            }
        }
        return defaultValue
    }

    open fun getStringAsIntOrNull(key: String?): Int? =
        if (contains(key)) getStringAsInt(key) else null

    open fun put(key: String?, value: String?): Boolean = putString(key, value)

    open fun putString(key: String?, value: String?): Boolean =
        editor.putString(key, value).commit()

    open fun save(key: String?, value: String?) = saveString(key, value)

    open fun saveString(key: String?, value: String?) = editor.putString(key, value).apply()

    open fun getStringSet(key: String?): Set<String> =
        preferences.getStringSet(key, DEFAULT_STRING_SET) ?: DEFAULT_STRING_SET

    open fun getStringSet(key: String?, defaultValue: Set<String?>?): Set<String> =
        preferences.getStringSet(key, defaultValue) ?: DEFAULT_STRING_SET

    open fun getStringSetOrNull(key: String?): Set<String>? =
        if (contains(key)) getStringSet(key) else null

    open fun put(key: String?, value: Set<String?>?): Boolean = putStringSet(key, value)

    open fun putStringSet(key: String?, value: Set<String?>?): Boolean =
        editor.putStringSet(key, value).commit()

    open fun save(key: String?, value: Set<String?>?) = saveStringSet(key, value)

    open fun saveStringSet(key: String?, value: Set<String?>?) =
        editor.putStringSet(key, value).apply()

    open fun get(key: String?, defaultValue: Any?): Any? {
        return when (defaultValue) {
            is String -> getString(key, defaultValue)
            is Int -> getInt(key, defaultValue)
            is Boolean -> getBoolean(key, defaultValue)
            is Float -> getFloat(key, defaultValue)
            is Long -> getLong(key, defaultValue)
            is Set<*> -> getStringSet(key, defaultValue as Set<String?>)
            else -> null
        }
    }

    open fun getAll(): Map<String, *> = preferences.all

    open fun contains(key: String?): Boolean = preferences.contains(key)

    open fun remove(key: String?): Boolean {
        val editor = preferences.edit()
        return if (contains(key)) editor.remove(key).commit() else false
    }

    open fun clear(): Boolean {
        val editor = preferences.edit()
        return editor.clear().commit()
    }

    open fun registerOnSharedPreferenceChangeListener(listener: OnSharedPreferenceChangeListener?) {
        preferences.registerOnSharedPreferenceChangeListener(listener)
    }

    open fun unregisterOnSharedPreferenceChangeListener(listener: OnSharedPreferenceChangeListener?) {
        preferences.unregisterOnSharedPreferenceChangeListener(listener)
    }

    companion object {
        @JvmStatic val PREFERENCES = "prefs"
        @JvmStatic val DEFAULT_BOOLEAN = false
        @JvmStatic val DEFAULT_FLOAT = 0F
        @JvmStatic val DEFAULT_INTEGER = 0
        @JvmStatic val DEFAULT_LONG = 0L
        @JvmStatic val DEFAULT_STRING = ""
        @JvmStatic val DEFAULT_STRING_SET = emptySet<String>()

        fun getPreferencesHelper(context: Context?): SharedPreferencesHelper {
            return SharedPreferencesHelper(context, PREFERENCES)
        }
    }
}