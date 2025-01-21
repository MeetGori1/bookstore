package com.meet.bookstore.domain

import com.meet.bookstore.domain.dtos.AuthorSummaryDto

data class BookSummary(
    var isbn: String,
    var title: String,
    var description: String,
    var image: String,
    var author: AuthorSummary
)
