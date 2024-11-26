package com.example.bookshelfremake.ui.screens.detail_screen

import com.example.bookshelfremake.model.Book

sealed interface DetailUiState {
    data class Success(val bookItem: Book): DetailUiState
    object Loading: DetailUiState
    object Error: DetailUiState
}