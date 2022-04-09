package com.memoeslink.helper

fun SharedPreferencesHelper.removeTemp(): Boolean =
    removePrefsByCategory(SharedPreferencesCategory.TEMP)

fun SharedPreferencesHelper.removePrefsByCategory(category: SharedPreferencesCategory?): Boolean {
    var preferenceDeleted = false

    if (category == null) return preferenceDeleted

    for (key in getAll().keys) {
        if (key.startsWith(category.prefix)) {
            if (remove(key)) preferenceDeleted = true
        }
    }
    return preferenceDeleted
}