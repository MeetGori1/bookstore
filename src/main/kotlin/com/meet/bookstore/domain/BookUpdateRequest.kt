package com.meet.bookstore.domain

data class BookUpdateRequest(
    var title: String?,
    var description: String?,
    var image: String?,
)
