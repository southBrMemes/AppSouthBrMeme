package com.example.dev.southbrmemes.Presenter.Rest.Service.Reload

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
object MemeList{
    fun getMeme(load:Boolean = true,activity: Activity, success: (note: Response<List<Meme>?>) -> Unit,
               failure: (throwable: Throwable) -> Unit) {


        val objectService = ObjectService()

        if(load)
        objectService.getDialog(activity,"Carregando meme")

        val retrofit = Retrofit.Builder()
                .baseUrl(URL.WEB_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(IMeme::class.java)

        val call = retrofit.getMemes()
        call.enqueue(object : Callback<List<Meme>?> {
            override fun onResponse(call: Call<List<Meme>?>?, response: Response<List<Meme>?>?) {
                response?.let {
                    success.invoke(it)
                }
                objectService.closeProgress()
            }

            override fun onFailure(call: Call<List<Meme>?>?, t: Throwable?) {
                failure.invoke(t!!)
                objectService.closeProgress()
            }
        })
    }
}