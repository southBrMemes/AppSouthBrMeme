package com.example.dev.southbrmemes.Presenter.ChangesScreen

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.example.dev.southbrmemes.Model.Session.SessionManager

/**
 * Created by dev on 10/05/2018.
 */
class ChangesActivity : AppCompatActivity() {
    companion object {

        fun changeActivity(classe: Class<*>, activity: Activity) {
            val it = Intent(activity, classe)
            activity.startActivity(it)
        }

        fun changeActivityNoReturn(classe: Class<*>, activity: Activity) {

            SessionManager(activity).cleanPreferences()

            val it = Intent(activity, classe)

            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            activity.startActivity(it)
        }
    }


}