package com.example.dev.southbrmemes.Presenter.Rest.Service.Data

import android.app.Activity
import com.example.dev.southbrmemes.Model.BussnesRule.ObjectService
import com.example.dev.southbrmemes.Model.Entity.Meme
import com.example.dev.southbrmemes.Model.Entity.Return
import com.example.dev.southbrmemes.Model.Entity.User
import com.example.dev.southbrmemes.Model.Session.SessionManager
import com.example.dev.southbrmemes.Presenter.Rest.Connect.URL
import com.example.dev.southbrmemes.Presenter.Rest.Interface.IMeme
import com.example.dev.southbrmemes.Presenter.Rest.Interface.IUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by dev on 13/05/2018.
 */
object UserData {
    fun data(activity: Activity, success: (note: Response<User?>) -> Unit,
               failure: (throwable: Throwable) -> Unit) {


        var objectService = ObjectService()
        objectService.getDialog(activity,"buscando dados")

        val retrofit = Retrofit.Builder()
                .baseUrl(URL.WEB_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(IUser::class.java)

        val call = retrofit.data(SessionManager(activity).getPreferences(SessionManager.TOKEN))
        call.enqueue(object : Callback<User?> {
            override fun onResponse(call: Call<User?>?, response: Response<User?>?) {
                response?.let {
                    success.invoke(it)
                }
                objectService.closeProgress()
            }

            override fun onFailure(call: Call<User?>?, t: Throwable?) {
                failure.invoke(t!!)
                objectService.closeProgress()
            }
        })
    }
}