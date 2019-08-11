package com.rcosteira.core.data.cache.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

interface BaseDao<T> {

    @Insert
    fun insert(obj: T)

    @Insert
    fun insert(vararg obj: T)

    @Insert
    fun insert(objs: List<T>)

    @Update
    fun update(obj: T)

    @Delete
    fun delete(obj: T)

    @Delete
    fun delete(vararg obj: T)
}