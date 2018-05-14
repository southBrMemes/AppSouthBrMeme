package com.example.dev.southbrmemes.Presenter.Rest.Interface

import com.example.dev.southbrmemes.Model.Entity.Return
import com.example.dev.southbrmemes.Model.Entity.User
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by dev on 13/05/2018.
 */
interface IUser{

    @GET("")
    fun data(@Header("Authorization") token: String): Call<User>

    @POST("")
    fun login(@Body user:User ): Call<Return>

    @POST("")
    fun insert(@Body user:User): Call<Return>

    @PUT("")
    fun update(@Body user:User,@Header("Authorization") token: String): Call<Return>

    @DELETE("")
    fun delete(@Header("Authorization") token: String): Call<Return>

}