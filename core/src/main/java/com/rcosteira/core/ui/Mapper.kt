package com.rcosteira.core.ui

interface Mapper<in D, out V> {

    fun mapToUI(type: D): V
}