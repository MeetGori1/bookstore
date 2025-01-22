package com.meet.bookstore.services

import com.meet.bookstore.domain.AuthorUpdateRequest
import com.meet.bookstore.domain.BookSummary
import com.meet.bookstore.domain.entities.AuthorEntity
import com.meet.bookstore.domain.entities.BookEntity

interface BookServices {

    fun createUpdate(isbn: String, bookSummary: BookSummary): Pair<BookEntity, Boolean>

    fun list(authorId: Long?): List<BookEntity>

}