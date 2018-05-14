package com.example.dev.southbrmemes.Presenter.Rest.Domain.Update

import android.app.Activity
import com.example.dev.southbrmemes.Model.Entity.Meme
import com.example.dev.southbrmemes.Presenter.Message.Message
import com.example.dev.southbrmemes.Presenter.Rest.Service.Update.MemeUpdate

/**
 * Created by dev on 13/05/2018.
 */
class MemeUpdateDomain(activity: Activity) {
    lateinit var activity: Activity

    init {
        this.activity = activity
    }

    fun update(id: Int, url: String, commit: String) {
        MemeUpdate.update(meme = Meme(id = id, url = url, commit = commit,name = null,dateCri = null), activity = activity, success = { s ->
            s?.let {
                if (it.code() == 200) {

                    val response = it.body()
                    response?.let {
                            Message.messageReturn(response.answerText!!, activity)
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