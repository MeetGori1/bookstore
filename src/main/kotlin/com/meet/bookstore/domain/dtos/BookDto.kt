package com.meet.bookstore.domain.dtos

data class BookDto(
    var isbn: String,
    var title: String,
    var description: String,
    var image: String,
    var author: AuthorDto
)
