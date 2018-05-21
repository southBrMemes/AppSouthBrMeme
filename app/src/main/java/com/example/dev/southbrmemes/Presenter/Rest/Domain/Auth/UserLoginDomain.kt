package com.example.dev.southbrmemes.Presenter.Rest.Domain.Auth

import android.app.Activity
import android.support.design.widget.Snackbar
import android.widget.Toast
import com.example.dev.southbrmemes.Model.Entity.Meme
import com.example.dev.southbrmemes.Model.Entity.User
import com.example.dev.southbrmemes.Model.Infra.Entity.UserEntity
import com.example.dev.southbrmemes.Model.Infra.Repository.UserRepository.IUserRepository
import com.example.dev.southbrmemes.Model.Infra.Repository.UserRepository.UserRepository
import com.example.dev.southbrmemes.Model.Session.SessionManager
import com.example.dev.southbrmemes.Presenter.ChangesScreen.ChangesActivity
import com.example.dev.southbrmemes.Presenter.Message.Message
import com.example.dev.southbrmemes.Presenter.Rest.Service.Auth.UserLogin
import com.example.dev.southbrmemes.Presenter.Rest.Service.Insert.MemeInsert
import com.example.dev.southbrmemes.Presenter.Rest.Service.Insert.UserInsert
import com.example.dev.southbrmemes.View.Activity.LoggedActivity

/**
 * Created by dev on 13/05/2018.
 */
class UserLoginDomain(activity: Activity){

    lateinit var _IUserRepository: IUserRepository
    lateinit var activity: Activity

    init {
        this._IUserRepository = UserRepository(activity)
        this.activity = activity
    }

    fun login(login: String, password: String) {
        UserLogin.login(User(login = login, password = password,name = null,cidade = null,newPassword = null), activity = activity, success = { s ->
            s?.let {
                if (it.code() == 200) {

                    val response = it.body()

                    response?.let {

                        Message.messageReturn(response.answerText!!,activity)

                        if(response.answer) {
                            Message.messageReturn(response.answerText!!,activity)

                            if(_IUserRepository.buscarToken() != null)
                                _IUserRepository.update(UserEntity(1,response.token!!))
                            else
                                _IUserRepository.insert(UserEntity(1,response.token!!))

                            Toast.makeText(activity, "toque no icone do aplicativo, para ver os memes um por um", Toast.LENGTH_LONG).show()
                            ChangesActivity.changeActivityNoReturn(LoggedActivity::class.java, activity)
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