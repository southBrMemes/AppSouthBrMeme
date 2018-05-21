package com.example.dev.southbrmemes.View.Fragment


import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.dev.southbrmemes.Model.BussnesRule.*
import com.example.dev.southbrmemes.Presenter.Message.Message
import com.example.dev.southbrmemes.Presenter.Rest.Domain.Insert.MemeInsertDomain
import com.example.dev.southbrmemes.R
import kotlinx.android.synthetic.main.fragment_register_meme.*
import java.io.File
import java.io.ByteArrayOutputStream
import java.util.*
import android.R.attr.data
import android.support.v4.app.NotificationCompat.getExtras
import android.graphics.Bitmap
import android.graphics.BitmapFactory






/**
 * A simple [Fragment] subclass.
 */
class RegisterMemeFragment : Fragment() {

    companion object {
        fun getInstance(): RegisterMemeFragment {
            return RegisterMemeFragment();
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_meme, container, false)
    }

    private val PERMISSAO_REQUEST = 2
    private val GALLERY = 1
    private val CAMERA = 3
    private val PHOTO_CAMERA = 4

    private var archive: File? = null

    private var imagOfPost: ImageView? = null

    private var rotation = 0

    private var aws: AWS? = null
    private var acessPhoto: AccessGalleryOfPhoto? = null
    private var acessSavePhotoGallery: SavePhotoGallery? = null
    private var acessPhotoCamera: AccessPhoto? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar!!.setTitle("Novo Meme")
        (activity as AppCompatActivity).supportActionBar!!.setIcon(null)

        aws = AWS()
        acessSavePhotoGallery = SavePhotoGallery()
        acessPhotoCamera = AccessPhoto()
        acessPhoto = AccessGalleryOfPhoto()

        acessPhoto!!.permissionAccessGallery(activity!!, PERMISSAO_REQUEST)
        acessSavePhotoGallery!!.permissionAccess(activity!!, PERMISSAO_REQUEST)

        imagOfPost = activity!!.findViewById<View>(R.id.imgRegisterMeme) as ImageView


        imgGalleryRegisterMeme!!.setOnClickListener { v ->
            if (acessPhoto!!.callAccessGallery(activity!!)) {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                rotation = 1
                startActivityForResult(intent, GALLERY)
            } else {
                Message.messageReturn("", activity!!)
            }
        }

        imgCameraRegisterMeme!!.setOnClickListener { v ->
            if (acessPhoto!!.callAccessGallery(activity!!)) {
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (takePictureIntent.resolveActivity(activity!!.packageManager) != null) {
                    try {

                        archive = acessSavePhotoGallery!!.createFile()

                        if (archive != null) {
                            val photoURI = FileProvider.getUriForFile(activity!!.baseContext,
                                    activity!!.baseContext.applicationContext.packageName + ".provider", archive!!)
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                            startActivityForResult(takePictureIntent, PHOTO_CAMERA)
                        }
                    } catch (t: Throwable) {
                        t.stackTrace
                    }

                }
            } else {
                Message.messageReturn("", activity!!)
            }
        }

        btnRegisterMeme.setOnClickListener { v ->
            if (ValidateComponent.validadeRegisterMeme(archive, editTextRegisterCommitMeme)) {

                val bitmap = BitmapFactory.decodeFile(archive?.absolutePath)
                val bytes = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, bytes);
                val b = bytes.toByteArray()
                val encodedfile = android.util.Base64.encodeToString(b, Base64.DEFAULT)

                MemeInsertDomain(activity!!).insert(encodedfile,editTextRegisterCommitMeme.text.toString())
                //aws!!.uploadImgMeme(activity!!,"SouthBRMeme" + System.currentTimeMillis() + ".jpg",editTextRegisterCommitMeme.text.toString(),archive!!,v)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        acessPhoto!!.permissionGallery(requestCode, grantResults, PERMISSAO_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val filePhotoGallery: File? = acessPhoto!!.accessGallery(requestCode, resultCode, data, imagOfPost!!, activity!!)

        val filePhotoCamera: File? = acessPhotoCamera!!.accessPhoto(requestCode, resultCode, data, CAMERA, imagOfPost!!, activity!!)

        if (filePhotoGallery != null) {
            rotation = 1
            archive = filePhotoGallery
        } else if (filePhotoCamera != null) {
            rotation = 1
            archive = filePhotoCamera
        }

        acessSavePhotoGallery!!.recordPhoto(requestCode, resultCode, PHOTO_CAMERA, activity!!, imagOfPost!!, archive!!, rotation)
    }

}// Required empty public constructor
