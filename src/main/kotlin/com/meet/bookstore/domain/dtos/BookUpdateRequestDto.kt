package com.meet.bookstore.domain.dtos

data class BookUpdateRequestDto(
    var title: String?,
    var description: String?,
    var image: String?,
)
