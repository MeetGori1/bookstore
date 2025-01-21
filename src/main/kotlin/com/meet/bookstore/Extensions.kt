package com.meet.bookstore

import com.meet.bookstore.domain.AuthorSummary
import com.meet.bookstore.domain.AuthorUpdateRequest
import com.meet.bookstore.domain.BookSummary
import com.meet.bookstore.domain.dtos.AuthorDto
import com.meet.bookstore.domain.dtos.AuthorSummaryDto
import com.meet.bookstore.domain.dtos.AuthorUpdateRequestDto
import com.meet.bookstore.domain.dtos.BookSummaryDto
import com.meet.bookstore.domain.entities.AuthorEntity
import com.meet.bookstore.domain.entities.BookEntity
import com.meet.bookstore.exceptions.InvalidAuthorException

fun AuthorEntity.toAuthorDto() = AuthorDto(
    id = this.id,
    name = this.name,
    age = this.age,
    description = this.description,
    image = this.image
)

fun AuthorDto.toAuthorEntity() = AuthorEntity(
    id = this.id,
    name = this.name,
    age = this.age,
    description = this.description,
    image = this.image
)

fun AuthorEntity.toAuthorSummaryDto(): AuthorSummaryDto {
    val authorId = this.id ?: throw InvalidAuthorException()

    return AuthorSummaryDto(
        id = authorId,
        name = this.name,
        image = this.image
    )
}

fun AuthorUpdateRequestDto.toAuthorUpdateRequest() = AuthorUpdateRequest(
    id = this.id,
    name = this.name,
    age = this.age,
    description = this.description,
    image = this.image
)

fun BookSummary.toBookEntity(author: AuthorEntity) = BookEntity(
    isbn = this.isbn,
    title = this.title,
    description = this.description,
    image = this.image,
    authorEntity = author
)

fun AuthorSummaryDto.toAuthorSummary() = AuthorSummary(
    id = this.id,
    name = this.name,
    image = this.image

)

fun BookSummaryDto.toBookSummary() = BookSummary(
    isbn = this.isbn,
    title = this.title,
    description = this.description,
    image = this.image,
    author = this.author.toAuthorSummary()
)

fun BookEntity.BookSummaryDto() = BookSummaryDto(
    isbn = this.isbn,
    title = this.title,
    description = this.description,
    image = this.image,
    author = authorEntity.toAuthorSummaryDto()
)