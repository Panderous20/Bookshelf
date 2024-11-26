package com.example.bookshelfremake.model

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: String,
    val volumeInfo: VolumeInfo,
    val saleInfo: SaleInfo
){

}

@Serializable
data class VolumeInfo(
    val title: String,
    val subtitle: String,
    val description: String,
    val imageLinks: ImageLinks? = null,
    val authors: List<String>,
    val publisher: String,
    val publishedDate: String,
) {
//    val allAuthorsx: String
//        get() = allAuthors()

    fun allAuthors(): String {
        var x = ""
        for(author in authors) {
            x += "$author, "
        }
        return x.trimEnd(',', ' ')
    }
}

//Vì ảnh sử dụng http mà android mặc định chặn http nên cần thêm
//android:usesCleartextTraffic="true" trước khi sử dụng
@Serializable
data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String,
) {
    //Khi sử dụng HTTPS, bạn có thể đảm bảo rằng các tài nguyên như hình ảnh hoặc các liên kết khác được tải xuống một cách an toàn và bảo mật hơn.
//    val httpsThumbnail : String
//        get() = thumbnail.replace("http", "https")
}

@Serializable
data class SaleInfo(
    val country: String?,
    val isEbook: Boolean,
    val saleability: String?,
    val listPrice: ListPrice?
) {
    val getPrice: String
        get() ="${listPrice?.amount?: "N/A"} ${listPrice?.currencyCode?: ""}"
}

@Serializable
data class ListPrice(
    val amount: Float?,
    val currencyCode: String?
)
