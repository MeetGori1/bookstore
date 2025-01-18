package com.meet.bookstore.repositories

import com.meet.bookstore.domain.Author
import com.meet.bookstore.domain.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookInterface:JpaRepository<Book,String>