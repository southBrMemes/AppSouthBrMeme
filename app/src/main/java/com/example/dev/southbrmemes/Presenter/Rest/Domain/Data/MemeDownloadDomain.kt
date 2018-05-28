package com.example.dev.southbrmemes.Presenter.Rest.Domain.Data

import android.annotation.TargetApi
import android.app.Activity
import android.os.Build
import com.example.dev.southbrmemes.Model.BussnesRule.SavePhotoGallery
import com.example.dev.southbrmemes.Presenter.Message.Message
import com.example.dev.southbrmemes.Presenter.Rest.Service.Data.MemeDownload
import java.io.FileOutputStream
import java.util.*

/**
 * Created by dev on 13/05/2018.
 */
class MemeDownloadDomain(activity: Activity) {

    lateinit var activity: Activity

    init {
        this.activity = activity
    }


    @TargetApi(Build.VERSION_CODES.O)
    fun download(id: Int) {
        MemeDownload.getDownload(id, activity = activity, success = { s ->
            s?.let {
                if (it.code() == 200) {
                    val response = it.body()

                    if (response!!.image != null) {

                        activity.runOnUiThread{

                                val image = Base64.getDecoder().decode(response.image)

                                val save = SavePhotoGallery()

                                val img = save.createFile()

                                val outputStream = FileOutputStream(img, false)
                                outputStream.write(image, 0, image!!.size)
                                outputStream.flush()
                                outputStream.close()

                                Message.messageReturn("download realizado com sucesso.", activity)
                            }
                        } else {
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