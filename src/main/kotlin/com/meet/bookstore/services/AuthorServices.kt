package com.meet.bookstore.services

import com.meet.bookstore.domain.entities.AuthorEntity

interface AuthorServices {

    fun create(authorEntity: AuthorEntity): AuthorEntity

    fun list(): List<AuthorEntity>

    fun get(id: Long): AuthorEntity?

    fun fullUpdate(id: Long,authorEntity: AuthorEntity): AuthorEntity
}