package com.meet.bookstore.services.impl

import com.meet.bookstore.domain.BookSummary
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

}