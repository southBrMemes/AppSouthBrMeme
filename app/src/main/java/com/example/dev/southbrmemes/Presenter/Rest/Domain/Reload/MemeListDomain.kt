package com.example.dev.southbrmemes.Presenter.Rest.Domain.Reload

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import br.com.zellar.hooh.Views.Adapters.MemeAdapter
import com.example.dev.southbrmemes.Model.Entity.Meme
import com.example.dev.southbrmemes.Presenter.Message.Message
import com.example.dev.southbrmemes.Presenter.Rest.Service.Reload.MemeList

/**
 * Created by dev on 13/05/2018.
 */
class MemeListDomain(activity: Activity) {

    lateinit var activity: Activity

    init {
        this.activity = activity
    }

    fun list(list: RecyclerView) {
        MemeList.getMeme(activity = activity, success = { s ->
            s?.let {
                if (it.code() == 200) {
                    val response = it.body() as ArrayList

                    val adapter = MemeAdapter(activity, response)
                    list.setAdapter(adapter)
                    val layout = LinearLayoutManager(activity,
                            LinearLayoutManager.VERTICAL, false)
                    list.setLayoutManager(layout)

                    list.setOnScrollListener(object : RecyclerView.OnScrollListener() {

                        internal var scrollDy = true

                        override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                            super.onScrollStateChanged(recyclerView, newState)

                        }

                        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {

                            val scrollPosition = layout.findLastVisibleItemPosition()

                            if (((adapter.itemCount - 1) - 5) == scrollPosition && scrollDy) {
                                scrollDy = false
                                refresh { lista ->
                                    adapter.getAdd(lista)
                                    adapter.notifyItemInserted(adapter.getItemCount())
                                    scrollDy = true
                                }

                            }
                        }
                    })
                } else {
                    Message.messageReturn("falha", activity)
                }
            }
        }, failure = { e ->
            Message.messageReturn("falha", activity)
        })
    }

    fun refresh(success: (note: ArrayList<Meme>) -> Unit) {
        MemeList.getMeme(false, activity = activity, success = { s ->
            s?.let {
                success.invoke(it.body() as ArrayList)
            }

        }, failure = { e ->
            Message.messageReturn("falha", activity)
        })
    }
}
