package com.meet.bookstore.services.impl

import com.meet.bookstore.domain.entities.AuthorEntity
import com.meet.bookstore.repositories.AuthorRepository
import com.meet.bookstore.services.AuthorServices
import org.springframework.stereotype.Service

@Service
interface AuthorServicesImpl(private val authorRepository: AuthorRepository) : AuthorServices {

    override fun save(authorEntity: AuthorEntity): AuthorEntity {
        return authorRepository.save(authorEntity)
    }
}