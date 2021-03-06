package com.example.dev.southbrmemes.Presenter.Rest.Service.Data

import android.app.Activity
import com.example.dev.southbrmemes.Model.BussnesRule.ObjectService
import com.example.dev.southbrmemes.Model.Entity.Meme
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
object MemeData {
    fun getMeme(activity: Activity, success: (note: Response<Meme?>) -> Unit,
                failure: (throwable: Throwable) -> Unit) {

        var objectService = ObjectService()
        objectService.getDialog(activity,"Carregando meme")

        val retrofit = Retrofit.Builder()
                .baseUrl(URL.WEB_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(IMeme::class.java)

        val call = retrofit.data()
        call.enqueue(object : Callback<Meme?> {
            override fun onResponse(call: Call<Meme?>?, response: Response<Meme?>?) {
                response?.let {
                    success.invoke(it)
                }
                objectService.closeProgress()
            }

            override fun onFailure(call: Call<Meme?>?, t: Throwable?) {
                failure.invoke(t!!)
                objectService.closeProgress()
            }
        })
    }
}