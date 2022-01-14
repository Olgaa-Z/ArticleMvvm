package com.lauwba.newsmvvm.repository.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.lauwba.newsmvvm.model.Source

class Converters {

    @TypeConverter
    fun fromSource(name : String): Source{
        return Source(name, name )
    }
}