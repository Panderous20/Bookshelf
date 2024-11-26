package com.example.bookshelfremake.data

import com.example.bookshelfremake.model.Book
import com.example.bookshelfremake.network.BookshelfApiService

class DefaultBookshelfRepository(
    private val bookshelfApiService: BookshelfApiService
): BookshelfRepository {
    override suspend fun getBooks(query: String): List<Book>? {
        return try {
            val res = bookshelfApiService.getBooks(query)
            if(res.isSuccessful) {
                res.body()?.items ?: emptyList()
            } else {
                emptyList()
            }
        } catch(e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun getbook(id: String): Book? {
        return try {
            val res = bookshelfApiService.getBook(id)
            if(res.isSuccessful) {
                res.body()
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}