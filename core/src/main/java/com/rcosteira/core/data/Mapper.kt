package com.rcosteira.core.data

interface Mapper<E, D> {

    fun mapToEntity(type: D): E
    fun mapFromEntity(type: E): D
}