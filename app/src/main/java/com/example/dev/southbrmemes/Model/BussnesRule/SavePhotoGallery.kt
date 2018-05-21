package com.example.dev.southbrmemes.Model.BussnesRule

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by dev on 13/05/2018.
 */
class SavePhotoGallery {

    private var fileName: String? = null

    fun getFileName(): String? {
        return fileName
    }

    @Throws(IOException::class)
    fun createFile(): File {
        this.fileName = filename()
        val pasta = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES + "/SouthBrMeme")
        return File(pasta.path + "/"
                + fileName)
    }

    fun filename(): String {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        return "SouthBrMeme_$timeStamp.jpg"
    }

    fun recordPhoto(requestCode: Int, resultCode: Int, Tirar_Foto: Int, activity: Activity, imageView: ImageView, arquivoFoto: File, camera: Int) {
        if (requestCode == Tirar_Foto && resultCode == android.app.Activity.RESULT_OK) {
            if (camera == 0) {
                ajustaFoto(arquivoFoto)
                activity.sendBroadcast(Intent(
                        Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                        Uri.fromFile(arquivoFoto))
                )
            } else {
                activity.sendBroadcast(Intent(
                        Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                        Uri.fromFile(arquivoFoto))
                )
            }
            displayImage(imageView, arquivoFoto, camera)
        }
    }

    fun displayImage(imagem: ImageView, arquivoFoto: File, camera: Int) {
        val targetW = imagem.width
        val targetH = imagem.height
        val bmOptions = BitmapFactory.Options()
        bmOptions.inJustDecodeBounds = true
        BitmapFactory.decodeFile(arquivoFoto.absolutePath, bmOptions)
        val photoW = bmOptions.outWidth
        val photoH = bmOptions.outHeight
        val scaleFactor = Math.min(photoW / targetW, photoH / targetH)
        bmOptions.inJustDecodeBounds = false
        bmOptions.inSampleSize = scaleFactor
        val bitmap = BitmapFactory.decodeFile(arquivoFoto.absolutePath, bmOptions)
        imagem.setImageBitmap(bitmap)
        imagem.scaleType = ImageView.ScaleType.FIT_XY
    }


    fun permissionAccess(activity: Activity, PERMISSAO_REQUEST: Int): Boolean {
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                return false
            } else {
                ActivityCompat.requestPermissions(activity,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        PERMISSAO_REQUEST)
                return false
            }
        }
        return true
    }

    fun ajustaFoto(arquivo: File) {
        val myBitmap = BitmapFactory.decodeFile(arquivo.absolutePath)

        val matrix = Matrix()
        matrix.postRotate(90f)

        val bitmapReduzido = Bitmap.createBitmap(myBitmap, 0, 0,
                myBitmap.width, myBitmap.height,
                matrix, true)

        val bos = ByteArrayOutputStream()
        bitmapReduzido.compress(Bitmap.CompressFormat.JPEG, 90 /*ignored for PNG*/, bos)
        val bitmapdata = bos.toByteArray()

        var imgToSdcard: File? = null
        try {
            imgToSdcard = arquivo
            val outputStream = FileOutputStream(imgToSdcard, false)
            outputStream.write(bitmapdata, 0, bitmapdata.size)
            outputStream.flush()
            outputStream.close()

        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}