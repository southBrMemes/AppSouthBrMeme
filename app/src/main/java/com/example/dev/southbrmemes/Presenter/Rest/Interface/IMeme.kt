package com.example.dev.southbrmemes.Presenter.Rest.Interface

import com.example.dev.southbrmemes.Model.Entity.Meme
import com.example.dev.southbrmemes.Model.Entity.Return
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by dev on 13/05/2018.
 */
interface IMeme {

    @POST("")
    fun insert(@Body meme: Meme, @Header("Authorization") token: String): Call<Return>

    @PUT("")
    fun update(@Body meme: Meme, @Header("Authorization") token: String): Call<Return>

    @DELETE("")
    fun delete(@Path("id") id: Int,@Header("Authorization") token: String): Call<Return>

    @GET("")
    fun getMemes(): Call<List<Meme>>

    @GET("")
    fun getMyMemes(@Header("Authorization") token: String): Call<List<Meme>>
}