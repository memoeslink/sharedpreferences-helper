package com.memoeslink.helper

import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import androidx.preference.PreferenceManager

class SharedPreferencesHelper : ContextWrapper {
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

    fun getBoolean(key: String?): Boolean = preferences.getBoolean(key, DEFAULT_BOOLEAN)

    fun getBoolean(key: String?, defaultValue: Boolean): Boolean =
        preferences.getBoolean(key, defaultValue)

    fun getBooleanOrNull(key: String?): Boolean? = if (contains(key)) getBoolean(key) else null

    fun put(key: String?, value: Boolean): Boolean = putBoolean(key, value)

    fun putBoolean(key: String?, value: Boolean): Boolean = editor.putBoolean(key, value).commit()

    fun apply(key: String?, value: Boolean) = applyBoolean(key, value)

    fun applyBoolean(key: String?, value: Boolean) = editor.putBoolean(key, value).apply()

    fun getFloat(key: String?): Float = preferences.getFloat(key, DEFAULT_FLOAT)

    fun getFloat(key: String?, defaultValue: Float): Float = preferences.getFloat(key, defaultValue)

    fun getFloatOrNull(key: String?): Float? = if (contains(key)) getFloat(key) else null

    fun put(key: String?, value: Float): Boolean = putFloat(key, value)

    fun putFloat(key: String?, value: Float): Boolean = editor.putFloat(key, value).commit()

    fun apply(key: String?, value: Float) = applyFloat(key, value)

    fun applyFloat(key: String?, value: Float) = editor.putFloat(key, value).apply()

    fun getInt(key: String?): Int = preferences.getInt(key, DEFAULT_INTEGER)

    fun getInt(key: String?, defaultValue: Int): Int = preferences.getInt(key, defaultValue)

    fun getIntOrNull(key: String?): Int? = if (contains(key)) getInt(key) else null

    fun put(key: String?, value: Int): Boolean = putInt(key, value)

    fun putInt(key: String?, value: Int): Boolean = editor.putInt(key, value).commit()

    fun apply(key: String?, value: Int) = applyInt(key, value)

    fun applyInt(key: String?, value: Int) = editor.putInt(key, value).apply()

    fun getLong(key: String?): Long = preferences.getLong(key, DEFAULT_LONG)

    fun getLong(key: String?, defaultValue: Long): Long = preferences.getLong(key, defaultValue)

    fun getLongOrNull(key: String?): Long? = if (contains(key)) getLong(key) else null

    fun put(key: String?, value: Long): Boolean = putLong(key, value)

    fun putLong(key: String?, value: Long): Boolean = editor.putLong(key, value).commit()

    fun apply(key: String?, value: Long) = applyLong(key, value)

    fun applyLong(key: String?, value: Long) = editor.putLong(key, value).apply()

    fun getString(key: String?): String? = preferences.getString(key, DEFAULT_STRING)

    fun getString(key: String?, defaultValue: String?): String? =
        preferences.getString(key, defaultValue)

    fun getStringOrNull(key: String?): String? = if (contains(key)) getString(key) else null

    fun getStringAsInt(key: String?): Int = getStringAsInt(key, DEFAULT_INTEGER)

    fun getStringAsInt(key: String?, defaultValue: Int): Int {
        getString(key)?.let { s ->
            try {
                return Integer.parseInt(s)
            } catch (e: NumberFormatException) {
            }
        }
        return defaultValue
    }

    fun getStringAsIntOrNull(key: String?): Int? = if (contains(key)) getStringAsInt(key) else null

    fun put(key: String?, value: String?): Boolean = putString(key, value)

    fun putString(key: String?, value: String?): Boolean = editor.putString(key, value).commit()

    fun apply(key: String?, value: String?) = applyString(key, value)

    fun applyString(key: String?, value: String?) = editor.putString(key, value).apply()

    fun getStringSet(key: String?): Set<String> =
        preferences.getStringSet(key, DEFAULT_STRING_SET) ?: DEFAULT_STRING_SET

    fun getStringSet(key: String?, defaultValue: Set<String?>?): Set<String> =
        preferences.getStringSet(key, defaultValue) ?: DEFAULT_STRING_SET

    fun getStringSetOrNull(key: String?): Set<String>? =
        if (contains(key)) getStringSet(key) else null

    fun put(key: String?, value: Set<String?>?): Boolean = putStringSet(key, value)

    fun putStringSet(key: String?, value: Set<String?>?): Boolean =
        editor.putStringSet(key, value).commit()

    fun apply(key: String?, value: Set<String?>?) = applyStringSet(key, value)

    fun applyStringSet(key: String?, value: Set<String?>?) =
        editor.putStringSet(key, value).apply()

    fun get(key: String?, defaultValue: Any?): Any? {
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

    fun getAll(): Map<String, *> = preferences.all

    fun contains(key: String?): Boolean = preferences.contains(key)

    fun remove(key: String?): Boolean {
        val editor = preferences.edit()
        return if (contains(key)) editor.remove(key).commit() else false
    }

    fun clear(): Boolean {
        val editor = preferences.edit()
        return editor.clear().commit()
    }

    fun registerOnSharedPreferenceChangeListener(listener: OnSharedPreferenceChangeListener?) {
        preferences.registerOnSharedPreferenceChangeListener(listener)
    }

    fun unregisterOnSharedPreferenceChangeListener(listener: OnSharedPreferenceChangeListener?) {
        preferences.unregisterOnSharedPreferenceChangeListener(listener)
    }

    companion object {
        private const val PREFERENCES = "prefs"
        private const val DEFAULT_BOOLEAN = false
        private const val DEFAULT_FLOAT = 0F
        private const val DEFAULT_INTEGER = 0
        private const val DEFAULT_LONG = 0L
        private const val DEFAULT_STRING = ""
        private val DEFAULT_STRING_SET = emptySet<String>()

        fun getPreferencesHelper(context: Context?): SharedPreferencesHelper {
            return SharedPreferencesHelper(context, PREFERENCES)
        }
    }
}