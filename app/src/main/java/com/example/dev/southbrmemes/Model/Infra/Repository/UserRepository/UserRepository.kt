package com.example.dev.southbrmemes.Model.Infra.Repository.UserRepository

import android.content.Context
import com.example.dev.southbrmemes.Model.Infra.DAO.UserDAO
import com.example.dev.southbrmemes.Model.Infra.Entity.UserEntity
import com.example.dev.southbrmemes.Model.Infra.Repository.AbstractRepository
import com.google.gson.reflect.TypeToken


/**
 * Created by dev on 13/05/2018.
 */

class UserRepository(context: Context) : AbstractRepository<UserDAO, UserEntity>(context, object : TypeToken<UserDAO>() {}.type), IUserRepository {
    override fun buscarToken(): UserEntity? {
        return execute<UserEntity?> {
            _dao.buscarToken()
        }
    }

    override fun insert(type: UserEntity) {
        execute<Unit> {
            _dao.insert(type)
        }
    }

    override fun update(type: UserEntity) {
        execute<Unit> {
            _dao.update(type)
        }
    }

    override fun delete(type: UserEntity) {
        execute<Unit> {
            _dao.delete(type)
        }
    }
}