package com.example.bookshelfremake.ui.screens.detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelfremake.BookshelfApplication
import com.example.bookshelfremake.data.BookshelfRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class DetailViewModel(
    val bookshelfRepository: BookshelfRepository
): ViewModel() {
    val _uiStateDetail = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiStateDetail = _uiStateDetail.asStateFlow()

    fun getBook(id: String) {
        viewModelScope.launch {
            _uiStateDetail.value = try {
                val book = bookshelfRepository.getbook(id)
                if (book == null) {
                    DetailUiState.Error
                } else {
                    DetailUiState.Success(book)
                }
            } catch (e: Exception) {
                DetailUiState.Error
            } catch (e: IOException) {
                DetailUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BookshelfApplication)
                val bookshelfRepository = application.container.bookshelfRepository
                DetailViewModel(bookshelfRepository = bookshelfRepository)
            }
        }
    }

}