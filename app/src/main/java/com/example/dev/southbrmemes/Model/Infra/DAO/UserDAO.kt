package com.example.dev.southbrmemes.Model.Infra.DAO;

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.dev.southbrmemes.Model.Infra.Entity.UserEntity

/**
 * Created by dev on 13/05/2018.
 */
@Dao
abstract class UserDAO : IDao<UserEntity>() {

    @Query("")
    abstract fun buscarToken(): UserEntity?
}