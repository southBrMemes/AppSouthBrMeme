package com.example.dev.southbrmemes.Model.Infra.Repository

import android.content.Context
import com.example.dev.southbrmemes.Model.BussnesRule.Thred
import com.example.dev.southbrmemes.Model.Infra.DAO.IDao
import com.example.dev.southbrmemes.Model.Infra.DataBase.DataBase
import com.example.dev.southbrmemes.Model.Infra.DataBase.DataBaseSingleton
import java.lang.reflect.Type


/**
 * Created by dev on 13/05/2018.
 */
abstract class AbstractRepository<T, E> where T : IDao<E> {
    protected var _dao: T

    private lateinit var c: Class<T>

    constructor(context: Context, type: Type) {
        _dao = dbSet<T>(context, type)
    }

    fun <U> dbSet(context: Context, type: Type): U where U : IDao<E> {
        return DataBase::class.java.methods.find { x -> x.returnType == type }?.invoke(
                DataBaseSingleton.getInstance(context)) as U
    }

    public fun <U> execute(action: () -> U?): U? {
        var result: U? = null

        Thred.start {
            try {
                result = action()
            } catch (t: Throwable) {
                result = null
            }
        }
        return result
    }
}