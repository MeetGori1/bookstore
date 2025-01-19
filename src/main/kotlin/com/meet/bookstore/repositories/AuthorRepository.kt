package com.meet.bookstore.repositories

import com.meet.bookstore.domain.entities.AuthorEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository:JpaRepository<AuthorEntity,Long?>