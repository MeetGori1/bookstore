package com.meet.bookstore.controllers

import com.meet.bookstore.domain.dtos.AuthorDto
import com.meet.bookstore.domain.entities.AuthorEntity
import com.meet.bookstore.services.AuthorServices
import com.meet.bookstore.toAuthorDto
import com.meet.bookstore.toAuthorEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/v1/authors"])
class AuthorsController(private val authorServices: AuthorServices) {

    @PostMapping
    fun createAuthor(@RequestBody authorDto: AuthorDto): AuthorDto {
        return authorServices.save(authorDto.toAuthorEntity()).toAuthorDto()
    }

    @GetMapping
    fun readManyUsers(): List<AuthorDto> {
        return authorServices.list().map { it.toAuthorDto() }
    }
}