package br.com.zellar.hooh.Views.Adapters

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.dev.southbrmemes.Model.BussnesRule.AWS
import com.example.dev.southbrmemes.Model.Entity.Meme
import com.example.dev.southbrmemes.Presenter.RecyclerView.ViewHolder.MemeViewHolder
import com.example.dev.southbrmemes.R
import com.squareup.picasso.Picasso


/**
 * Created by dev on 08/03/2018.
 */
class MemeAdapter(context: Activity, itensMemes: List<Meme>) : RecyclerView.Adapter<MemeViewHolder>() {

    private var _context = context
    private var _itensMemes: List<Meme>

    init {
        this._itensMemes = itensMemes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder {
        var view: View = LayoutInflater.from(parent?.context).inflate(R.layout.list_meme, parent, false)

        return MemeViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {

        var item = getItem(position)

        holder.name.setText(item.name)

        Picasso.get().
                load("${AWS.URL}${item?.url}")
                .into(holder.imgMeme);
        holder.imgMeme.setScaleType(ImageView.ScaleType.FIT_XY)

        holder.commit.setText(item.commit)
    }

    override fun getItemCount(): Int {
        return _itensMemes.size
    }

    fun getItem(position: Int): Meme {
        return _itensMemes.get(position)
    }

}