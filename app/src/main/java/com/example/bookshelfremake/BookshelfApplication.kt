package com.example.bookshelfremake

import android.app.Application
import com.example.bookshelfremake.di.AppContainer
import com.example.bookshelfremake.di.DefaultAppContainer

class BookshelfApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}