package com.example.moviecatalog.data

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}