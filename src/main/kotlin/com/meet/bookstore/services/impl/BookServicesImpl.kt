package com.meet.bookstore.services.impl

import com.meet.bookstore.domain.BookSummary
import com.meet.bookstore.domain.BookUpdateRequest
import com.meet.bookstore.domain.entities.BookEntity
import com.meet.bookstore.repositories.AuthorRepository
import com.meet.bookstore.repositories.BookRepository
import com.meet.bookstore.services.BookServices
import com.meet.bookstore.toBookEntity
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BookServicesImpl(private val bookRepository: BookRepository, private val authorRepository: AuthorRepository) :
    BookServices {
    @Transactional
    override fun createUpdate(isbn: String, bookSummary: BookSummary): Pair<BookEntity, Boolean> {
        val normalisedBook = bookSummary.copy(isbn = isbn)
        val isExist = bookRepository.existsById(isbn)
        val author = authorRepository.findByIdOrNull(bookSummary.author.id)
        checkNotNull(author)
        val saveBook = bookRepository.save(normalisedBook.toBookEntity(author))
        return Pair(saveBook, isExist.not())
    }

    override fun list(authorId: Long?): List<BookEntity> {
        return authorId?.let {
            bookRepository.findByAuthorEntityId(authorId)
        } ?: bookRepository.findAll()
    }

    override fun get(isbn: String): BookEntity? {
        return bookRepository.findByIdOrNull(isbn)
    }

    override fun partialUpdate(isbn: String, bookUpdateRequest: BookUpdateRequest): BookEntity {
        val existingBook = bookRepository.findByIdOrNull(isbn)
        checkNotNull(existingBook)
        existingBook.copy(
            title = bookUpdateRequest.title ?: existingBook.title,
            description = bookUpdateRequest.description ?: existingBook.description,
            image = bookUpdateRequest.image ?: existingBook.image
        )
        return bookRepository.save(existingBook)
    }

    override fun delete(isbn: String) {
        return bookRepository.deleteById(isbn)
    }

}