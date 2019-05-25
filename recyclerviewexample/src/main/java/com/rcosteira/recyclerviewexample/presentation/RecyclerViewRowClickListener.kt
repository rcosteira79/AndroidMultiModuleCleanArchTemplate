package com.rcosteira.recyclerviewexample.presentation

interface RecyclerViewRowClickListener<in T> {
    fun onRowClicked(item: T, position: Int)
}