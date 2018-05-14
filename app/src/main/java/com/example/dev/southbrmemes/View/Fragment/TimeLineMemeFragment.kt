package com.example.dev.southbrmemes.View.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.zellar.hooh.Views.Adapters.MemeAdapter
import com.example.dev.southbrmemes.Model.Entity.Meme
import com.example.dev.southbrmemes.Presenter.Rest.Domain.Reload.MemeListDomain

import com.example.dev.southbrmemes.R
import kotlinx.android.synthetic.main.fragment_time_line.*


/**
 * A simple [Fragment] subclass.
 */
class TimeLineMemeFragment : Fragment() {

    companion object {
        fun getInstance(): TimeLineMemeFragment {
            return TimeLineMemeFragment();
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time_line, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar!!.setIcon(R.mipmap.trollicon)

        MemeListDomain(activity!!).list(listTimeLineMeme)

        refreshTimeLineMeme.setOnRefreshListener {
            refreshTimeLineMeme.isRefreshing = false
            MemeListDomain(activity!!).list(listTimeLineMeme)
        }
    }

}
