package com.meet.bookstore.controllers

import com.meet.bookstore.domain.dtos.AuthorDto
import com.meet.bookstore.services.AuthorServices
import com.meet.bookstore.toAuthorDto
import com.meet.bookstore.toAuthorEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

    @GetMapping(path = ["/{id}"])
    fun getOneAuthor(@PathVariable("id") id: Long): ResponseEntity<AuthorDto> {
        val foundAuthor = authorServices.get(id)?.toAuthorDto()
        return foundAuthor?.let {
            ResponseEntity(it, HttpStatus.OK)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)
    }
}