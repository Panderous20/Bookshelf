package com.example.bookshelfremake.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.bookshelfremake.R
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookshelfremake.ui.theme.BookShelfRemakeTheme

@Composable
fun NothingFoundScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.ic_not_found),
            contentDescription = null,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = stringResource(R.string.no_records_found_msg)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NothingFoundScreenPreview() {
    BookShelfRemakeTheme {
        NothingFoundScreen()
    }
}