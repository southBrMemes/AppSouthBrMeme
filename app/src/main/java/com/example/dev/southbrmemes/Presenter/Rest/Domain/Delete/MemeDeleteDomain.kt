package com.example.dev.southbrmemes.Presenter.Rest.Domain.Delete

import android.app.Activity
import com.example.dev.southbrmemes.Presenter.ChangesScreen.ChangesActivity
import com.example.dev.southbrmemes.Presenter.Message.Message
import com.example.dev.southbrmemes.Presenter.Rest.Service.Delete.MemeDelete
import com.example.dev.southbrmemes.View.Activity.IndexActivity

/**
 * Created by dev on 13/05/2018.
 */
class MemeDeleteDomain(activity: Activity) {
    lateinit var activity: Activity

    init {
        this.activity = activity
    }

    fun delete(id: Int) {
        MemeDelete.delete(id = id,activity = activity, success = { s ->
            s?.let {
                if (it.code() == 200) {

                    val response = it.body()
                    response?.let {
                        Message.messageReturn(response.answerText!!, activity)
                        if (response.answer)
                            ChangesActivity.changeActivityNoReturn(IndexActivity::class.java, activity)
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