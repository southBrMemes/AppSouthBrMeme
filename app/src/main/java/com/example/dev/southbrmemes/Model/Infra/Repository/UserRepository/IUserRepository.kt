package com.example.dev.southbrmemes.Model.Infra.Repository.UserRepository

import com.example.dev.southbrmemes.Model.Infra.Entity.UserEntity
import com.example.dev.southbrmemes.Model.Infra.Repository.IRepository


/**
 * Created by dev on 13/05/2018.
 */
interface IUserRepository : IRepository<UserEntity> {
    fun buscarToken(): UserEntity?
}