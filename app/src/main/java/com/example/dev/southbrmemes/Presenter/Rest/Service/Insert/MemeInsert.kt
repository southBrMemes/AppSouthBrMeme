package com.example.dev.southbrmemes.Presenter.Rest.Service.Insert

import android.app.Activity
import com.example.dev.southbrmemes.Model.BussnesRule.ObjectService
import com.example.dev.southbrmemes.Model.Entity.Meme
import com.example.dev.southbrmemes.Model.Entity.Return
import com.example.dev.southbrmemes.Model.Session.SessionManager
import com.example.dev.southbrmemes.Presenter.Rest.Connect.URL
import com.example.dev.southbrmemes.Presenter.Rest.Interface.IMeme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by dev on 13/05/2018.
 */
object MemeInsert{
    fun insert(meme: Meme, activity: Activity, success: (note: Response<Return?>) -> Unit,
               failure: (throwable: Throwable) -> Unit) {


        var objectService = ObjectService()
        objectService.getDialog(activity,"cadastrando meme")

        val retrofit = Retrofit.Builder()
                .baseUrl(URL.WEB_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(IMeme::class.java)

        val call = retrofit.insert(meme, SessionManager(activity).getPreferences(SessionManager.TOKEN))
        call.enqueue(object : Callback<Return?> {
            override fun onResponse(call: Call<Return?>?, response: Response<Return?>?) {
                response?.let {
                    success.invoke(it)
                }
                objectService.closeProgress()
            }

            override fun onFailure(call: Call<Return?>?, t: Throwable?) {
                failure.invoke(t!!)
                objectService.closeProgress()
            }
        })
    }
}