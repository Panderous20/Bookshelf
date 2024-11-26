package com.example.bookshelfremake.data

import com.example.bookshelfremake.model.Book

interface BookshelfRepository {

    suspend fun getBooks(query: String): List<Book>?

    suspend fun getbook(id: String): Book?
}