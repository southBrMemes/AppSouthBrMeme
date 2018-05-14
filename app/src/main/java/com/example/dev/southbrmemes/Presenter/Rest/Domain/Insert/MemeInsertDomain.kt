package com.example.dev.southbrmemes.Presenter.Rest.Domain.Insert

import android.app.Activity
import com.example.dev.southbrmemes.Model.Entity.Meme
import com.example.dev.southbrmemes.Presenter.Message.Message
import com.example.dev.southbrmemes.Presenter.Rest.Service.Insert.MemeInsert
import com.example.dev.southbrmemes.View.PopUp.PopUpRegisterMeme

/**
 * Created by dev on 13/05/2018.
 */
class MemeInsertDomain(activity: Activity) {

    lateinit var activity: Activity

    init {
        this.activity = activity
    }

    fun insert(url: String, commit: String) {
        MemeInsert.insert(Meme(url = url, commit = commit,name = null,dateCri = null), activity = activity, success = { s ->
            s?.let {
                if (it.code() == 200) {

                    val response = it.body()
                    response?.let {

                        Message.messageReturn(response.answerText!!, activity)

                        PopUpRegisterMeme(activity).creatPopUp()
                    }

                } else {
                    Message.messageReturn("falha", activity)
                }
            }
        }, failure = { e ->
            Message.messageReturn("falha", activity)
        })
    }
}