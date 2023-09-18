package com.example.musicapp.helper

import android.content.Context
import android.content.res.Configuration
import com.example.jetpack_compose_sample_app.helper.FileStorageHelper
import com.example.jetpack_compose_sample_app.helper.SharedPreferenceHelper
import com.example.musicapp.api.model.Results
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

object Utils {
    fun saveLocale(context:Context, languageCode: String){
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = Configuration(resources.configuration)
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
        SharedPreferenceHelper(context).save("language",languageCode)
    }
    fun updateLocale(context:Context){
        val languageCode = SharedPreferenceHelper(context).getValueString("language")
        saveLocale(context, languageCode ?: "en")
    }
}