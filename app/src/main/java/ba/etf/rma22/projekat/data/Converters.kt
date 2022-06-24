package ba.etf.rma22.projekat.data

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimstamp(value : Long?) : Date?{
        return value?.let {Date(it)}
    }

    @TypeConverter
    fun dateToTimestamp(date : Date?): Long? {
        return date?.time?.toLong()
    }
}