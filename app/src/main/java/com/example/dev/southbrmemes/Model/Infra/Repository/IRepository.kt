package com.example.dev.southbrmemes.Model.Infra.Repository

/**
 * Created by dev on 13/05/2018.
 */

interface IRepository<T> {
    fun insert(type: T)
    fun update(type: T)
    fun delete(type: T)
}