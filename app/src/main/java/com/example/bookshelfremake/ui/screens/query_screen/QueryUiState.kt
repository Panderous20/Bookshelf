package com.example.bookshelfremake.ui.screens.query_screen

import com.example.bookshelfremake.model.Book

sealed interface QueryUiState {
    data class Success(val bookList: List<Book>): QueryUiState
    object Loading: QueryUiState
    object Error: QueryUiState
}