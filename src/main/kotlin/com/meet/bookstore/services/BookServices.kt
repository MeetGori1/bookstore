package com.meet.bookstore.services

import com.meet.bookstore.domain.BookSummary
import com.meet.bookstore.domain.BookUpdateRequest
import com.meet.bookstore.domain.entities.BookEntity

interface BookServices {

    fun createUpdate(isbn: String, bookSummary: BookSummary): Pair<BookEntity, Boolean>

    fun list(authorId: Long?): List<BookEntity>

    fun get(isbn: String): BookEntity?

    fun partialUpdate(isbn: String, bookUpdateRequest: BookUpdateRequest): BookEntity

    fun delete(isbn: String): Unit
}