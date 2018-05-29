package com.example.dev.southbrmemes.Model.Infra.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by dev on 13/05/2018.
 */
@Entity(tableName = "")
data class UserEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "")
        var id: Int = 0,

        @ColumnInfo(name = "")
        var token: String = ""
)