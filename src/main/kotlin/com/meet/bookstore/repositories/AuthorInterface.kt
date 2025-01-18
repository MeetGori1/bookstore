package com.meet.bookstore.repositories

import com.meet.bookstore.domain.Author
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorInterface:JpaRepository<Author,Long?>