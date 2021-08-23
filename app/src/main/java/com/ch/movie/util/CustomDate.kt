package com.ch.movie.util

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class CustomDate : JsonDeserializer<Date> {
    private val df = SimpleDateFormat("yyyy-MM-dd")
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Date? {
        Log.i("Date",json.toString())
        return try {
            if(json==null)
                null
            else
                df.parse(json.asString)
        }
        catch (e: ParseException){
            null
        }
    }
}