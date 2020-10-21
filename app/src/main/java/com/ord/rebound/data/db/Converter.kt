package com.ord.rebound.data.db


import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ord.rebound.data.model.Message
import java.util.*

class Converter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun dateFromTimestamp(timestamp: Long?): Date? = timestamp?.let { Date(timestamp) }

        @TypeConverter
        @JvmStatic
        fun dateToTimestamp(date: Date?): Long? = date?.time

        @TypeConverter
        @JvmStatic
        fun messageFromString(string: String?): Message? =
            Gson().fromJson(string, Message::class.java)

        @TypeConverter
        @JvmStatic
        fun messageToString(message: Message?): String? = Gson().toJson(message).toString()

        @TypeConverter
        @JvmStatic
        fun stringToMessageList(string: String?): ArrayList<Message>? =
            Gson().fromJson(string, object : TypeToken<ArrayList<Message>>() {}.type)

        @TypeConverter
        @JvmStatic
        fun messageListToString(messages: ArrayList<Message>?): String? = Gson().toJson(messages)
    }
}