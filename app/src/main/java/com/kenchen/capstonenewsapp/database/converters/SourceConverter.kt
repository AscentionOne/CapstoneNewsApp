package com.kenchen.capstonenewsapp.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kenchen.capstonenewsapp.model.Source


class SourceConverter() {

    private val gson = Gson()

    @TypeConverter
    fun fromSource(source: Source): String {
        return gson.toJson(source)
    }

    @TypeConverter
    fun toSource(json: String): Source {
        val sourceType = object : TypeToken<Source>() {}.type
        return gson.fromJson(json, sourceType)
    }
}
