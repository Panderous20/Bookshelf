package com.example.bookshelfremake.ui.screens.menu_screen

import androidx.annotation.StringRes

data class Option(
    @StringRes val label: Int,
    val onClick: () -> Unit
)
