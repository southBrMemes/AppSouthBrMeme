package com.example.dev.southbrmemes.Presenter.RecyclerView.ViewHolder

import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.dev.southbrmemes.R

/**
 * Created by dev on 12/05/2018.
 */
class MemeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var name: TextView
    var imgMeme: ImageView
    var commit: TextView
    var download : FloatingActionButton
    var send : FloatingActionButton

    init {
        name = view.findViewById<View>(R.id.textViewListNameMeme) as TextView
        imgMeme = view.findViewById<View>(R.id.imgListMeme) as ImageView
        commit = view.findViewById<View>(R.id.textViewListCommitMeme) as TextView
        download = view.findViewById<View>(R.id.fabDownload) as FloatingActionButton
        send = view.findViewById<View>(R.id.fabSend) as FloatingActionButton

    }
}