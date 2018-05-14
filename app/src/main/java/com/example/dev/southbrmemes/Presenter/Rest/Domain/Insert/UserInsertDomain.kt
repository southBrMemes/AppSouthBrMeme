package com.example.dev.southbrmemes.Presenter.Rest.Domain.Insert

import android.app.Activity
import com.example.dev.southbrmemes.Model.Entity.User
import com.example.dev.southbrmemes.Model.Infra.Entity.UserEntity
import com.example.dev.southbrmemes.Model.Infra.Repository.UserRepository.IUserRepository
import com.example.dev.southbrmemes.Model.Infra.Repository.UserRepository.UserRepository
import com.example.dev.southbrmemes.Presenter.ChangesScreen.ChangesActivity
import com.example.dev.southbrmemes.Presenter.Message.Message
import com.example.dev.southbrmemes.Presenter.Rest.Service.Insert.UserInsert
import com.example.dev.southbrmemes.View.Activity.LoggedActivity

/**
 * Created by dev on 13/05/2018.
 */
class UserInsertDomain(activity: Activity) {

    lateinit var _IUserRepository: IUserRepository
    lateinit var activity: Activity

    init {
        _IUserRepository = UserRepository(activity)
        this.activity = activity
    }

    fun insert(name: String, login: String, password: String) {
        UserInsert.insert(User(name = name, login = login, password = password, cidade = null, newPassword = null), activity = activity, success = { s ->
            s?.let {
                if (it.code() == 200) {

                    val response = it.body()

                    response?.let {
                        Message.messageReturn(response.answerText!!, activity)
                        if (response.answer) {

                            if (_IUserRepository.buscarToken() != null)
                                _IUserRepository.update(UserEntity(1, response.token!!))
                            else
                                _IUserRepository.insert(UserEntity(1, response.token!!))

                            ChangesActivity.changeActivity(LoggedActivity::class.java, activity)
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