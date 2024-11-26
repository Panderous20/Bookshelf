package com.example.bookshelfremake

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookshelfremake.ui.screens.detail_screen.DetailScreen
import com.example.bookshelfremake.ui.screens.detail_screen.DetailViewModel
import com.example.bookshelfremake.ui.screens.favorite_screen.FavoritesScreen
import com.example.bookshelfremake.ui.screens.menu_screen.MenuScreen
import com.example.bookshelfremake.ui.screens.query_screen.QueryScreen
import com.example.bookshelfremake.ui.screens.query_screen.QueryViewModel

@Composable
fun BookshelfNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val viewModel: QueryViewModel = viewModel(factory = QueryViewModel.Factory)

    NavHost(
        navController = navController,
        startDestination = AppDestinations.MenuScreen.name,
        modifier = modifier
    ) {
        composable(route = AppDestinations.MenuScreen.name) {
            MenuScreen(
                onSearchClick = {
                    navController.navigate(AppDestinations.QueryScreen.name)
                },
                onFavClick = {
                    navController.navigate(AppDestinations.FavoriteScreen.name)
                }
            )
        }
        composable(route = AppDestinations.QueryScreen.name) {
            QueryScreen(
                viewModel = viewModel,
                retryAction = {
                    viewModel.getBooks()
                },
                onDetailsClick = {
                    viewModel.selectedBookId = it.id
                    navController.navigate(AppDestinations.DetailScreen.name)
                }
            )
        }

        composable(route = AppDestinations.FavoriteScreen.name) {
            FavoritesScreen(
                viewModel = viewModel,
                bookshelfUiState = viewModel.favoritesfUiState,
                retryAction = {
                    viewModel.getBooks()
                },
                onDetailsClick = {
                    viewModel.selectedBookId = it.id
                    navController.navigate(AppDestinations.DetailScreen.name)
                }
            )
        }

        composable(route = AppDestinations.DetailScreen.name) {
            val detailViewModel : DetailViewModel = viewModel(factory = DetailViewModel.Factory)
            detailViewModel.getBook(viewModel.selectedBookId)

            DetailScreen(
                viewModel = detailViewModel,
                retryAction = { detailViewModel.getBook(viewModel.selectedBookId) },
            )
        }
    }
}