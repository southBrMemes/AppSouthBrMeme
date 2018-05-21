package com.example.dev.southbrmemes.Model.Session

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Created by dev on 10/05/2018.
 */
class SessionManager(private val context: Context){

    companion object{
        val TOKEN = "TOKEMSOUTH";
    }

    private val PREFERENCES = "MEMESSOUHTBR";

    fun setPreferences(key: String, value: String) {
        val sharedPreferences = this.context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getPreferences(key: String): String {
        val preferences = this.context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        val guid = preferences.getString(key, "")

        return guid
    }

    fun cleanPreferences() {
        val sharedPreferences = this.context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        preferences.edit().clear().commit()
        editor.clear().commit()
    }
}