package com.example.dev.southbrmemes.Model.BussnesRule

import android.app.Activity
import android.graphics.BitmapFactory
import android.os.Environment
import android.view.View
import android.widget.ImageView
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3Client
import com.example.dev.southbrmemes.Presenter.Message.Message
import com.example.dev.southbrmemes.Presenter.Rest.Domain.Insert.MemeInsertDomain
import com.example.dev.southbrmemes.Presenter.Rest.Domain.Update.MemeUpdateDomain
import com.example.dev.southbrmemes.Presenter.Rest.Service.Insert.MemeInsert
import java.io.File

/**
 * Created by dev on 12/05/2018.
 */

/**
 * Created by dev on 06/04/2018.
 */

class AWS {

    companion object {
        val URL = "https://bucketeer-acabba4c-461f-43ff-a3e7-a2db464195a2.s3.amazonaws.com/public/"
    }

    private val access = ""
    private val secret = ""
    private val bucket = "https://bucketeer-acabba4c-461f-43ff-a3e7-a2db464195a2.s3.amazonaws.com/public"
    private val service: ObjectService
    private val callAccessGallery: SavePhotoGallery

    private val myFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/SouthBrMeme")
    private var file: File? = null

    init {
        service = ObjectService()
        callAccessGallery = SavePhotoGallery()
    }


    fun uploadImgUpdateMeme(activity: Activity,id:Int, key: String, commit: String, file: File, view: View) {
        this.file = File(file.path)
        service.getDialogNocancel(activity, "aguarde...")
        val thread = Thread(Runnable {
            uploadAWSUpdateImgMeme(activity, this.file, id ,key, commit, view)
        })
        thread.start()
    }


    fun uploadImgMeme(activity: Activity, key: String, commit: String, file: File, view: View) {
        this.file = File(file.path)
        service.getDialogNocancel(activity, "aguarde...")
        val thread = Thread(Runnable {
            uploadAWSImgMeme(activity, this.file, key, commit, view)
        })
        thread.start()
    }


    fun download(activity: Activity, key: String?) {
        if (callAccessGallery.permissionAccess(activity, 12)) {
            if (key != null) {
                this.file = File(myFile.toString() + "/" + key)
                if (!this.file!!.exists()) {
                    val thread = Thread(Runnable {
                        downloadImg(activity, this.file, key)
                    })
                    thread.start()
                }
            }
        } else {
            Message.messageReturn("é necessarios acesso a galeria do celular", activity)
        }
    }


    private fun uploadAWSUpdateImgMeme(activity: Activity, file: File?,idkey:Int, key: String, commit: String, view: View) {
        val credentials = BasicAWSCredentials(this.access, this.secret)
        val s3Client = AmazonS3Client(credentials)
        s3Client.setRegion(com.amazonaws.regions.Region.getRegion(Regions.US_EAST_1))
        val transferUtility = TransferUtility(s3Client, activity)
        val transferObserver = transferUtility.upload(this.bucket, key, file)
        transferObserver.setTransferListener(object : TransferListener {
            override fun onStateChanged(id: Int, state: TransferState) {
                if (state == TransferState.COMPLETED) {
                    service.closeProgress()

                    MemeUpdateDomain(activity).update(idkey,key,commit)

                }
            }

            override fun onProgressChanged(id: Int, bytesCurrent: Long, bytesTotal: Long) {}

            override fun onError(id: Int, ex: Exception) {
                service.closeProgress()
                Message.messageSnackbar("Erro ao realizar o upload", view)
            }
        })
    }

    private fun uploadAWSImgMeme(activity: Activity, file: File?, key: String, commit: String, view: View) {
        val credentials = BasicAWSCredentials(this.access, this.secret)
        val s3Client = AmazonS3Client(credentials)
        s3Client.setRegion(com.amazonaws.regions.Region.getRegion(Regions.US_EAST_1))
        val transferUtility = TransferUtility(s3Client, activity)
        val transferObserver = transferUtility.upload(this.bucket, key, file)
        transferObserver.setTransferListener(object : TransferListener {
            override fun onStateChanged(id: Int, state: TransferState) {
                if (state == TransferState.COMPLETED) {
                    service.closeProgress()

                    MemeInsertDomain(activity).insert(key,commit)

                }
            }

            override fun onProgressChanged(id: Int, bytesCurrent: Long, bytesTotal: Long) {}

            override fun onError(id: Int, ex: Exception) {
                service.closeProgress()
                Message.messageSnackbar("Erro ao realizar o upload", view)
            }
        })
    }

    private fun downloadImg(activity: Activity, file: File?, key: String?) {
        val credentials = BasicAWSCredentials(this.access, this.secret)
        val s3Client = AmazonS3Client(credentials)
        s3Client.setRegion(com.amazonaws.regions.Region.getRegion(Regions.US_EAST_1))
        val transferUtility = TransferUtility(s3Client, activity)

        val transferObserver = transferUtility.download(this.bucket, key, file)
        transferObserver.setTransferListener(object : TransferListener {
            override fun onStateChanged(id: Int, state: TransferState) {
                if (state == TransferState.COMPLETED) {

                    Message.messageReturn("Download realizado com sucesso", activity)
                }

                if (state == TransferState.FAILED) {
                    Message.messageReturn("Falha ao realizar o Download", activity)
                }
            }

            override fun onProgressChanged(id: Int, bytesCurrent: Long, bytesTotal: Long) {}

            override fun onError(id: Int, ex: Exception) {
                Message.messageReturn("Erro ao realizar o Download", activity)
            }
        })
    }
}
