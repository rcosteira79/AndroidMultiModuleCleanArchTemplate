package com.rcosteira.core.ui

interface Mapper<in E, out V> {

    fun mapToUI(type: E): V
}