package by.overpass.poms23.data.db

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OptionsConverter {

    private val gson = Gson()
    private val listType = object : TypeToken<List<String>>() {}.type;

    @TypeConverter
    fun toJson(options: List<String>): String {
        return gson.toJson(options, listType)
    }

    @TypeConverter
    fun fromJson(json: String): List<String> {
        return gson.fromJson(json, listType)
    }


}