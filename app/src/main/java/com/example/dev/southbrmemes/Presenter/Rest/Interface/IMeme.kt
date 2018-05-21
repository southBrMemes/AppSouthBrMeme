package com.example.dev.southbrmemes.Presenter.Rest.Interface

import com.example.dev.southbrmemes.Model.Entity.Meme
import com.example.dev.southbrmemes.Model.Entity.Return
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by dev on 13/05/2018.
 */
interface IMeme {

    @POST("meme/insert")
    fun insert(@Body meme: Meme, @Header("Authorization") token: String): Call<Return>

    @PUT("meme/update")
    fun update(@Body meme: Meme, @Header("Authorization") token: String): Call<Return>

    @DELETE("meme/delete/{id}")
    fun delete(@Path("id") id: Int,@Header("Authorization") token: String): Call<Return>

    @GET("meme/list")
    fun getMemes(): Call<List<Meme>>

    @GET("meme/data")
    fun data(): Call<Meme>

    @GET("meme/mylist")
    fun getMyMemes(@Header("Authorization") token: String): Call<List<Meme>>
}