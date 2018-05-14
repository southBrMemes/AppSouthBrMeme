package com.example.dev.southbrmemes.Presenter.Rest.Domain.Data

import android.app.Activity
import android.widget.EditText
import com.example.dev.southbrmemes.Model.Entity.Return
import com.example.dev.southbrmemes.Model.Entity.User
import com.example.dev.southbrmemes.Presenter.Message.Message
import com.example.dev.southbrmemes.Presenter.Rest.Service.Data.UserData
import com.google.gson.Gson

/**
 * Created by dev on 13/05/2018.
 */
class UserDataDomain(activity: Activity) {

    lateinit var activity: Activity

    init {
        this.activity = activity
    }

    fun data(name: EditText, login: EditText) {
        UserData.data(activity = activity, success = { s ->
            s?.let {
                if (it.code() == 200) {

                    val response = it.body()

                    response?.let {

                        if (response != null) {
                            name.setText(response.name)
                            login.setText(response.login)

                        } else {
                            Message.messageReturn("falha", activity)
                        }
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