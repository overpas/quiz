package by.overpass.poms23.utils

import android.preference.PreferenceManager
import by.overpass.poms23.Poms23App

object PrefUtil {

    private val app_prefs = PreferenceManager.getDefaultSharedPreferences(Poms23App.getAppContext())

    fun putInt(key: String, value: Int) {
        app_prefs
                .edit()
                .putInt(key, value)
                .apply()
    }

    fun getInt(key: String): Int = app_prefs.getInt(key, 0)

}