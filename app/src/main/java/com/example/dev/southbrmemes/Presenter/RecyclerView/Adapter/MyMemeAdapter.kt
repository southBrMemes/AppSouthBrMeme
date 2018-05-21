package com.example.dev.southbrmemes.Presenter.RecyclerView.Adapter

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.dev.southbrmemes.Model.BussnesRule.AWS
import com.example.dev.southbrmemes.Model.Entity.Meme
import com.example.dev.southbrmemes.Presenter.RecyclerView.ViewHolder.MyMemeViewHolder
import com.example.dev.southbrmemes.R
import com.example.dev.southbrmemes.View.Fragment.EditMemeFragment
import com.squareup.picasso.Picasso

/**
 * Created by dev on 08/03/2018.
 */
class MyMemeAdapter(context: Activity,fragment: FragmentActivity, itensMemes: List<Meme>) : RecyclerView.Adapter<MyMemeViewHolder>() {

    private var _fragmentActivity = fragment
    private var _context = context
    private var _itensMemes: List<Meme>

    init {
        this._itensMemes = itensMemes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMemeViewHolder {
        var view: View = LayoutInflater.from(parent?.context).inflate(R.layout.list_my_meme, parent, false)

        return MyMemeViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyMemeViewHolder, position: Int) {

        var item = getItem(position)

        holder.name.setText(item.name)

        Picasso.get()
                .load("${AWS.URL}${item?.url}")
                .into(holder.imgMeme);
        holder.imgMeme.setScaleType(ImageView.ScaleType.FIT_XY)

        holder.commit.setText(item.commit)

        holder.fabEdit.setOnClickListener { v ->

            val arg = Bundle()

            arg.putInt("id", item.id)
            arg.putString("url", item.url)
            arg.putString("commit", item.commit)

            var editMemeFragment = EditMemeFragment.getInstance()
            editMemeFragment.arguments = arg

            chengefragment(editMemeFragment,R.id.flLogged)

        }
    }

    override fun getItemCount(): Int {
        return _itensMemes.size
    }

    fun getItem(position: Int): Meme {
        return _itensMemes.get(position)
    }



    fun chengefragment(`object`: Fragment, id: Int) {
        _fragmentActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(id, `object`, null)
                .addToBackStack("Fragment")
                .commit()
    }
}