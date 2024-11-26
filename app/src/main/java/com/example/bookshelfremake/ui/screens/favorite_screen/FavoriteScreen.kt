package com.example.bookshelfremake.ui.screens.favorite_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.bookshelfremake.R
import com.example.bookshelfremake.model.Book
import com.example.bookshelfremake.ui.screens.components.ErrorScreen
import com.example.bookshelfremake.ui.screens.components.LoadingScreen
import com.example.bookshelfremake.ui.screens.query_screen.GridList
import com.example.bookshelfremake.ui.screens.query_screen.QueryUiState
import com.example.bookshelfremake.ui.screens.query_screen.QueryViewModel

@Composable
fun FavoritesScreen(
    viewModel: QueryViewModel,
    bookshelfUiState: QueryUiState,
    retryAction: () -> Unit,
    onDetailsClick: (Book) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        if (!viewModel.favoriteBooks.isEmpty()) {
            when (bookshelfUiState) {
                is QueryUiState.Loading -> LoadingScreen(modifier)
                is QueryUiState.Success -> GridList(
                    bookshelfList = bookshelfUiState.bookList,
                    viewModel = viewModel,
                    modifier = modifier,
                    onDetailsClick = onDetailsClick
                )
                else -> ErrorScreen(retryAction, modifier)
            }
        } else {
            Box(modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(stringResource(R.string.NoFavoriteBooksText))
            }
        }
    }
}