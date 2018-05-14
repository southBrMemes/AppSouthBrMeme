package com.example.dev.southbrmemes.Presenter.Rest.Domain.Update

import android.app.Activity
import com.example.dev.southbrmemes.Model.Entity.User
import com.example.dev.southbrmemes.Presenter.Message.Message
import com.example.dev.southbrmemes.Presenter.Rest.Service.Update.UserUpdate

/**
 * Created by dev on 13/05/2018.
 */
class UserUpdateDomain(activity: Activity) {
    lateinit var activity: Activity

    init {
        this.activity = activity
    }

    fun update(name: String, login: String, password: String,newPassword:String) {
        UserUpdate.update(user = User(name = name, login = login, password = password, cidade = null,newPassword = newPassword), activity = activity, success = { s ->
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