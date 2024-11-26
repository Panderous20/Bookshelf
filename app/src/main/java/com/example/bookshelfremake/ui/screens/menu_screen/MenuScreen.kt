package com.example.bookshelfremake.ui.screens.menu_screen

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookshelfremake.R
import com.example.bookshelfremake.ui.theme.BookShelfRemakeTheme


@SuppressLint("SuspiciousIndentation")
@Composable
fun MenuScreen(
    onSearchClick: () -> Unit,
    onFavClick: () -> Unit,
) {
     val options = listOf(
         Option(R.string.btn_search, onSearchClick),
         Option(R.string.btn_favorite, onFavClick)
    )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            options.forEach {
                OptionsButton(
                    option = it.label,
                    onClick = it.onClick,
                    modifier = Modifier.fillMaxWidth()
                        .padding(8.dp)


                )
            }
        }
}

@Composable
fun OptionsButton(
    @StringRes option: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(onClick = onClick,modifier = modifier) {
        Text(text = stringResource(option), textAlign = TextAlign.Center)

    }
}

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    BookShelfRemakeTheme {
        MenuScreen(
            {}, {}
        )
    }
}