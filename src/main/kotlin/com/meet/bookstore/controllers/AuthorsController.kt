package com.meet.bookstore.controllers

import com.meet.bookstore.domain.dtos.AuthorDto
import com.meet.bookstore.domain.dtos.AuthorUpdateRequestDto
import com.meet.bookstore.services.AuthorServices
import com.meet.bookstore.toAuthorDto
import com.meet.bookstore.toAuthorEntity
import com.meet.bookstore.toAuthorUpdateRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/v1/authors"])
class AuthorsController(private val authorServices: AuthorServices) {

    @PostMapping
    fun createAuthor(@RequestBody authorDto: AuthorDto): ResponseEntity<AuthorDto> {
        return try {
            val createdAuthor = authorServices.create(
                authorDto.toAuthorEntity()
            ).toAuthorDto()
            ResponseEntity(createdAuthor, HttpStatus.CREATED)

        } catch (ex: IllegalArgumentException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
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


    @PutMapping(path = ["/{id}"])
    fun fullUpdateAuthor(@PathVariable("id") id: Long, @RequestBody authorDto: AuthorDto): ResponseEntity<AuthorDto> {
        return try {
            val updatedAuthor = authorServices.fullUpdate(id, authorDto.toAuthorEntity())
            ResponseEntity(updatedAuthor.toAuthorDto(), HttpStatus.OK)

        } catch (ex: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @PatchMapping(path = ["/{id}"])
    fun partialUpdateAuthor(
        @PathVariable("id") id: Long,
        @RequestBody authorUpdateDto: AuthorUpdateRequestDto
    ): ResponseEntity<AuthorDto> {
        return try {
            val updatedAuthor = authorServices.partialUpdate(id, authorUpdateDto.toAuthorUpdateRequest())
            ResponseEntity(updatedAuthor.toAuthorDto(), HttpStatus.OK)

        } catch (ex: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteAuthor(@PathVariable("id") id: Long): ResponseEntity<Unit> {
        authorServices.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}