package com.sipos.mmtrackerbackend.dto

import com.sipos.mmtrackerbackend.model.User
import org.springframework.stereotype.Component

data class UserDTORequest(
    var username: String,
    var password: String,
)

data class UserDTOResponse(
    var id: Long,
    var username: String,
)


@Component
class UserConverter : ResponseConverter<User, UserDTOResponse> {

    override fun convertToResponse(source: User): UserDTOResponse {
        return UserDTOResponse(
            id = source.id!!,
            username = source.username
        )
    }
}