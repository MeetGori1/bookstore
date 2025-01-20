package com.meet.bookstore.domain

data class AuthorUpdateRequest(
    var id: Long?,
    var name: String?,
    var age: Int?,
    var description: String?,
    var image: String?
)
