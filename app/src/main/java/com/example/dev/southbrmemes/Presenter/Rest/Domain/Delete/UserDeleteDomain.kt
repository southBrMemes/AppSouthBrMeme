package com.example.dev.southbrmemes.Presenter.Rest.Domain.Delete

import android.app.Activity
import com.example.dev.southbrmemes.Model.Entity.Meme
import com.example.dev.southbrmemes.Model.Infra.Repository.UserRepository.IUserRepository
import com.example.dev.southbrmemes.Model.Infra.Repository.UserRepository.UserRepository
import com.example.dev.southbrmemes.Presenter.ChangesScreen.ChangesActivity
import com.example.dev.southbrmemes.Presenter.Message.Message
import com.example.dev.southbrmemes.Presenter.Rest.Service.Delete.MemeDelete
import com.example.dev.southbrmemes.Presenter.Rest.Service.Delete.UserDelete
import com.example.dev.southbrmemes.Presenter.Rest.Service.Update.MemeUpdate
import com.example.dev.southbrmemes.View.Activity.IndexActivity

/**
 * Created by dev on 13/05/2018.
 */
class UserDeleteDomain(activity: Activity){
    lateinit var activity: Activity
    lateinit var _IUserRepository:IUserRepository

    init {
        this.activity = activity
        this._IUserRepository = UserRepository(activity)
    }

    fun delete() {
        UserDelete.delete(activity = activity, success = { s ->
            s?.let {
                if (it.code() == 200) {

                    val response = it.body()
                    response?.let {

                        Message.messageReturn(response.answerText!!, activity)

                        if (response.answer) {

                            if (_IUserRepository.buscarToken() != null)
                            _IUserRepository.delete(_IUserRepository.buscarToken()!!)

                            ChangesActivity.changeActivityNoReturn(IndexActivity::class.java,activity)
                        }
                    }

                } else {
                    Message.messageReturn("falha", activity)
                }
            }
        }, failure = { e ->
            Message.messageReturn("falha", activity)
        })
    }
}