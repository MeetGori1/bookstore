package com.meet.bookstore.controllers

import com.meet.bookstore.BookSummaryDto
import com.meet.bookstore.domain.dtos.BookSummaryDto
import com.meet.bookstore.exceptions.InvalidAuthorException
import com.meet.bookstore.services.BookServices
import com.meet.bookstore.toBookSummary
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BooksController(private val bookServices: BookServices) {
    @PutMapping(path = ["/v1/books/{isbn}"])
    fun createFullUpdateBook(
        @PathVariable("isbn") isbn: String, @RequestBody book: BookSummaryDto
    ): ResponseEntity<BookSummaryDto> {
        try {
            val (savedBook, isCreated) = bookServices.createUpdate(isbn, book.toBookSummary())
            val responseCode = if (isCreated) HttpStatus.CREATED else HttpStatus.OK
            return ResponseEntity(savedBook.BookSummaryDto(), responseCode)
        } catch (e: InvalidAuthorException) {
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: IllegalStateException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}