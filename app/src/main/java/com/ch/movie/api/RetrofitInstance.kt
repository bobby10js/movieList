package com.ch.movie.api
import android.util.Log
import com.ch.movie.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import java.util.Date
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonElement
import com.google.gson.JsonDeserializer
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat


object RetrofitInstance {
    private var gsonDate: Gson = GsonBuilder()
        .setDateFormat("YYYY-MM-DD")
        .registerTypeAdapter(Date::class.java,NewDate())
        .create()
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonDate))
            .build()
    }

    val API:TmdbAPI by lazy {
        retrofit.create(TmdbAPI::class.java)
    }

    class NewDate :JsonDeserializer<Date> {
        private val df = SimpleDateFormat("yyyy-MM-dd")
        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Date? {
            Log.i("Date",json.toString())
            return try {
                if(json==null)
                    null
                else
                    df.parse(json.asString)
            }
            catch (e:ParseException){
                null
            }
        }
    }

}