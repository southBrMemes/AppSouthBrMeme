package com.example.dev.southbrmemes.Presenter.Rest.Domain.Data

import android.app.Activity
import android.widget.ImageView
import android.widget.TextView
import com.example.dev.southbrmemes.Model.BussnesRule.AWS
import com.example.dev.southbrmemes.Presenter.Message.Message
import com.example.dev.southbrmemes.Presenter.Rest.Service.Data.MemeData
import com.squareup.picasso.Picasso



/**
 * Created by dev on 13/05/2018.
 */
class MemeDataDomain(activity: Activity) {

    lateinit var activity: Activity

    init {
        this.activity = activity
    }

    fun data(img: ImageView, name: TextView) {
        MemeData.getMeme(activity = activity, success = { s ->
            s?.let {
                if (it.code() == 200) {
                    val response = it.body()

                    Picasso.get()
                            .load("${AWS.URL}${response?.url}")
                            .into(img)
                    img.setScaleType(ImageView.ScaleType.FIT_XY)

                    name.setText(response?.name)

                } else {
                    Message.messageReturn("falha", activity)
                }
            }
        }, failure = { e ->
            Message.messageReturn("falha", activity)
        })
    }
}