package com.meet.bookstore.services.impl

import com.meet.bookstore.domain.AuthorUpdateRequest
import com.meet.bookstore.domain.entities.AuthorEntity
import com.meet.bookstore.repositories.AuthorRepository
import com.meet.bookstore.services.AuthorServices
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AuthorServicesImpl(private val authorRepository: AuthorRepository) : AuthorServices {

    override fun create(authorEntity: AuthorEntity): AuthorEntity {
        return authorRepository.save(authorEntity)
    }

    override fun list(): List<AuthorEntity> {
        return authorRepository.findAll()
    }

    override fun get(id: Long): AuthorEntity? {
        return authorRepository.findByIdOrNull(id)
    }

    @Transactional
    override fun fullUpdate(id: Long, authorEntity: AuthorEntity): AuthorEntity {
        check(authorRepository.existsById(id))
        val normalisedUser = authorEntity.copy(id = id)
        return authorRepository.save(normalisedUser)
    }

    override fun partialUpdate(id: Long, authorUpdate: AuthorUpdateRequest): AuthorEntity {
        val existingAuthor = authorRepository.findByIdOrNull(id)
        checkNotNull(existingAuthor)
        val updatedAuthor = existingAuthor.copy(
            name = authorUpdate.name ?: existingAuthor.name,
            age = authorUpdate.age ?: existingAuthor.age,
            description = authorUpdate.description ?: existingAuthor.description,
            image = authorUpdate.image ?: existingAuthor.image,
        )
        return authorRepository.save(updatedAuthor)
    }

    override fun delete(id: Long) {
        authorRepository.deleteById(id)
    }
}