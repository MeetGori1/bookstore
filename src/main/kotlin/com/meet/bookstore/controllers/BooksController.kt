package com.meet.bookstore.controllers

import com.meet.bookstore.toBookSummaryDto
import com.meet.bookstore.domain.dtos.BookSummaryDto
import com.meet.bookstore.domain.dtos.BookUpdateRequestDto
import com.meet.bookstore.exceptions.InvalidAuthorException
import com.meet.bookstore.services.BookServices
import com.meet.bookstore.toBookSummary
import com.meet.bookstore.toBookUpdateRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/v1/books"])
class BooksController(private val bookServices: BookServices) {

    @PutMapping(path = ["/{isbn}"])
    fun createFullUpdateBook(
        @PathVariable("isbn") isbn: String, @RequestBody book: BookSummaryDto
    ): ResponseEntity<BookSummaryDto> {
        try {
            val (savedBook, isCreated) = bookServices.createUpdate(isbn, book.toBookSummary())
            val responseCode = if (isCreated) HttpStatus.CREATED else HttpStatus.OK
            return ResponseEntity(savedBook.toBookSummaryDto(), responseCode)
        } catch (e: InvalidAuthorException) {
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: IllegalStateException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping
    fun readManyBooks(@RequestParam("author") authorId: Long?): List<BookSummaryDto> {
        return bookServices.list(authorId).map { it.toBookSummaryDto() }
    }

    @GetMapping(path = ["/{isbn}"])
    fun readOneBook(@PathVariable("isbn") isbn: String): ResponseEntity<BookSummaryDto> {
        return bookServices.get(isbn)?.let { ResponseEntity(it.toBookSummaryDto(), HttpStatus.OK) } ?: ResponseEntity(
            HttpStatus.NOT_FOUND
        )
    }

    @PatchMapping(path = ["/{isbn}"])
    fun partialUpdateBook(
        @PathVariable("isbn") isbn: String,
        @RequestBody bookUpdateRequestDto: BookUpdateRequestDto
    ): ResponseEntity<BookSummaryDto> {
        try {
            val updatedBook = bookServices.partialUpdate(isbn, bookUpdateRequestDto.toBookUpdateRequest())
            return ResponseEntity(updatedBook.toBookSummaryDto(), HttpStatus.OK)
        } catch (e: IllegalStateException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @DeleteMapping(path = ["/{isbn}"])
    fun deleteBook(@PathVariable("isbn") isbn: String): ResponseEntity<Unit> {
        bookServices.delete(isbn)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}