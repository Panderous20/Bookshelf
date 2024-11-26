package com.example.bookshelfremake.ui.screens.detail_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelfremake.R
import com.example.bookshelfremake.model.Book
import com.example.bookshelfremake.model.ListPrice
import com.example.bookshelfremake.model.SaleInfo
import com.example.bookshelfremake.model.VolumeInfo
import com.example.bookshelfremake.ui.screens.components.ErrorScreen
import com.example.bookshelfremake.ui.screens.components.LoadingScreen
import com.example.bookshelfremake.ui.theme.BookShelfRemakeTheme

@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiStateDetail = viewModel.uiStateDetail.collectAsState().value

    when(uiStateDetail) {
        is DetailUiState.Error -> ErrorScreen(retryAction)
        is DetailUiState.Loading -> LoadingScreen()
        is DetailUiState.Success -> BookDetail(uiStateDetail.bookItem)
    }
}

@Composable
fun BookDetail(
    bookItem: Book,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Column(

            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = buildAnnotatedString {
                    append("Title: ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(bookItem.volumeInfo.title)
                    }
                },
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(bookItem.volumeInfo.imageLinks?.thumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )
            Text(
                text = stringResource(R.string.book_subtitle,bookItem.volumeInfo.subtitle)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.book_authors, bookItem.volumeInfo.allAuthors())
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.book_publishedDate, bookItem.volumeInfo.publishedDate)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Saleability: " + bookItem.saleInfo.saleability
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.book_price, bookItem.saleInfo.getPrice)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Decription: \n" + bookItem.volumeInfo.description,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailsScreenPreview() {
    BookShelfRemakeTheme {
        val mockData =
            Book(
                id = "123",
                volumeInfo = VolumeInfo(
                    title = "A book",
                    description = "Caniss ortum, tanquam bassus exemplar.",
                    publishedDate = "11/11/2011",
                    authors =  listOf("AAA","aaa"),
                    publisher = "John Carter",
                    subtitle = "Cunu litist",
                    imageLinks = null,
                ),
                saleInfo = SaleInfo(
                    country = "USA",
                    isEbook = false,
                    saleability = "NOT_FOR_SALE",
                    listPrice = ListPrice(
                        amount = 2.22f,
                        currencyCode = "US Dollar"
                    )
                )
            )
        BookDetail(
            bookItem = mockData,
        )
    }
}