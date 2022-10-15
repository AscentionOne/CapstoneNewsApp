package com.kenchen.capstonenewsapp.database.converters

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.kenchen.capstonenewsapp.App
import com.kenchen.capstonenewsapp.model.Source

class SourceConverter {

    @TypeConverter
    fun fromSource(source: Source): String {
        return App.gson.toJson(source)
    }

    @TypeConverter
    fun toSource(json: String): Source {
        val sourceType = object : TypeToken<Source>() {}.type
        return App.gson.fromJson(json, sourceType)
    }
}
