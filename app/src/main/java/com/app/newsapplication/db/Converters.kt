package com.app.newsapplication.db

import androidx.room.TypeConverter
import com.app.newsapplication.data.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return "${source.id}-${source.name}"
    }

    @TypeConverter
    fun fromString(name: String): Source {
        return Source(name.split("-")[0], name.split("-")[1])
    }
}