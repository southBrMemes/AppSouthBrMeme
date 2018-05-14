package com.example.dev.southbrmemes.Presenter.Rest.Domain.Reload

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import br.com.zellar.hooh.Views.Adapters.MemeAdapter
import com.example.dev.southbrmemes.Presenter.Message.Message
import com.example.dev.southbrmemes.Presenter.Rest.Service.Reload.MemeList

/**
 * Created by dev on 13/05/2018.
 */
class MemeListDomain(activity: Activity) {

    lateinit var activity: Activity

    init {
        this.activity = activity
    }

    fun list(list: RecyclerView) {
        MemeList.getMeme(activity = activity, success = { s ->
            s?.let {
                if (it.code() == 200) {
                    val response = it.body()

                    list.setAdapter(MemeAdapter(activity, response!!))
                    val layout = LinearLayoutManager(activity,
                            LinearLayoutManager.VERTICAL, false)
                    list.setLayoutManager(layout)

                } else {
                    Message.messageReturn("falha", activity)
                }
            }
        }, failure = { e ->
            Message.messageReturn("falha", activity)
        })
    }
}
