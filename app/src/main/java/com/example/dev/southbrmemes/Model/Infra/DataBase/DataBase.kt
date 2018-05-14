package com.example.dev.southbrmemes.Model.Infra.DataBase;

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.dev.southbrmemes.Model.Infra.DAO.UserDAO
import com.example.dev.southbrmemes.Model.Infra.Entity.UserEntity

/**
 * Created by dev on 13/05/2018.
 */
@Database(entities = arrayOf(
        UserEntity::class),
        version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {

    companion object {
        public val DATABASE_NAME = ""
    }

    abstract fun users(): UserDAO
}