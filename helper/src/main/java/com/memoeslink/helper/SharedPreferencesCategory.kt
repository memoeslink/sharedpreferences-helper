package com.memoeslink.helper

enum class SharedPreferencesCategory(val prefix: String) {
    TEMP("temp_"),
    SYSTEM("system_"),
    USER("user_"),
    SETTING("setting_"),
    DEFAULT("default_"),
    GENERAL("general_"),
    COMMON("common_");
}