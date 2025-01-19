package com.meet.bookstore.services

import com.meet.bookstore.domain.entities.AuthorEntity

interface AuthorServices {

    fun save(authorEntity: AuthorEntity): AuthorEntity

    fun list():List<AuthorEntity>
}