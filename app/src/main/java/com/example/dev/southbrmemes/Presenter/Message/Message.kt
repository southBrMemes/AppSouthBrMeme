package com.example.dev.southbrmemes.Presenter.Message

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Toast

/**
 * Created by dev on 12/05/2018.
 */

/**
 * Created by Kayque Rodrigues on 19/01/2018.
 */

object Message {

    fun messageToast(texto: Int, activity: Activity) {
        Toast.makeText(activity, texto, Toast.LENGTH_SHORT).show()
    }

    fun messageReturn(texto: String, activity: Activity) {
        Toast.makeText(activity, texto, Toast.LENGTH_SHORT).show()
    }

    fun messageSnackbar(texto: Int, view: View) {
        Snackbar.make(view, texto, Snackbar.LENGTH_LONG).show()
    }
    fun messageSnackbar(texto: String, view: View) {
        Snackbar.make(view, texto, Snackbar.LENGTH_LONG).show()
    }
}
