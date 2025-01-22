package com.meet.bookstore.repositories

import com.meet.bookstore.domain.entities.BookEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<BookEntity, String> {
    fun findByAuthorEntityId(id: Long): List<BookEntity>
}