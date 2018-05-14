package com.example.dev.southbrmemes.View.PopUp

import android.app.Activity
import android.app.AlertDialog
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.dev.southbrmemes.R
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by dev on 13/05/2018.
 */
class PopUpRegisterMeme(activity: Activity) {

    lateinit var _activity: Activity

    init {
        _activity = activity
    }

    fun creatPopUp(){
        var dialog = AlertDialog.Builder(_activity).create()

        var view: View = _activity.getLayoutInflater().inflate(R.layout.pop_up_register_meme, null)
        dialog.setView(view)

        var listImg = ArrayList<Int>()
        listImg.add(R.drawable.gerador01)
        listImg.add(R.drawable.gerador02)
        listImg.add(R.drawable.gerador03)
        listImg.add(R.drawable.gerador04)
        listImg.add(R.drawable.gerador05)
        listImg.add(R.drawable.gerador07)
        listImg.add(R.drawable.gerador08)
        listImg.add(R.drawable.gerador09)
        listImg.add(R.drawable.gerador10)
        listImg.add(R.drawable.gerador11)
        listImg.add(R.drawable.gerador12)
        listImg.add(R.drawable.gerador13)
        listImg.add(R.drawable.gerador14)

        var imgMeme: ImageView = view.findViewById(R.id.imgPopUpRegisterMeme)

        var random = Random()
        imgMeme.setBackgroundResource(listImg.get(random.nextInt(listImg.size)))

        var btnPopUpOk: TextView = view.findViewById(R.id.textViewConfirmRegisterPopUp)

        if (!_activity.isFinishing()) {
            dialog.show()
        }

        btnPopUpOk.setOnClickListener{ v ->
            dialog.dismiss()
        }
    }
}