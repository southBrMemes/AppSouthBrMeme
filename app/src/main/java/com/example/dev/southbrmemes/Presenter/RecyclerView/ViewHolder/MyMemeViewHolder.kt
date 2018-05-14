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
class MyMemeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var name: TextView
    var imgMeme: ImageView
    var commit: TextView
    var fabEdit:FloatingActionButton

    init {
        name = view.findViewById<View>(R.id.textViewListNameMyMeme) as TextView
        imgMeme = view.findViewById<View>(R.id.imgListMyMeme) as ImageView
        commit = view.findViewById<View>(R.id.textViewListCommitMyMeme) as TextView
        fabEdit = view.findViewById<View>(R.id.fabEditMyMeme) as FloatingActionButton
    }
}