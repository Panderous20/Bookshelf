package com.example.bookshelfremake.di

import com.example.bookshelfremake.data.BookshelfRepository
import com.example.bookshelfremake.network.BookshelfApiService

interface AppContainer {
    val bookshelfApiService: BookshelfApiService
    val bookshelfRepository: BookshelfRepository
}