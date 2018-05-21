package com.example.dev.southbrmemes.View.PopUp

import android.app.Activity
import android.app.AlertDialog
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.dev.southbrmemes.Presenter.Rest.Domain.Data.MemeDataDomain
import com.example.dev.southbrmemes.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.fragment_time_line.*
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

    fun creatPopUpMenu() {
        var dialog = AlertDialog.Builder(_activity).create()

        var view: View = _activity.getLayoutInflater().inflate(R.layout.pop_up_menu_meme, null)
        dialog.setView(view)


        var imgMeme: ImageView = view.findViewById(R.id.imgPopUpRegisterMeme)

        var btnPopUpOk: TextView = view.findViewById(R.id.textViewConfirmRegisterPopUp)

        var name: TextView = view.findViewById(R.id.textViewNomeRegisterMeme)

        var btnPopUpRefrash: TextView = view.findViewById(R.id.textViewRefreshRegisterPopUp)

        var adView: AdView = view.findViewById(R.id.adView1)

        var adRequest = AdRequest.Builder()
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build()

        adView.loadAd(adRequest)

        if (!_activity.isFinishing()) {
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()

            MemeDataDomain(activity = _activity).data(imgMeme, name)
        }

        btnPopUpOk.setOnClickListener { v ->
            dialog.dismiss()
        }

        btnPopUpRefrash.setOnClickListener { v ->
            MemeDataDomain(activity = _activity).data(imgMeme, name)
        }
    }
}