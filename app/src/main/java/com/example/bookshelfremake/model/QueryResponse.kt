package com.example.bookshelfremake.model

import com.google.gson.annotations.SerializedName

data class QueryResponse(
    @SerializedName("kind") val kind: String,
    @SerializedName("totalItems") val totalItems: Int,
    @SerializedName("items") val items: List<Book>
)
