package com.sipos.mmtrackerbackend.dto

import com.sipos.mmtrackerbackend.model.User
import org.springframework.stereotype.Component

class UserDTO(
    var username: String,
    var password: String,
)

class UserDTOWithId(
    var id: Long,
    var username: String,
)

@Component
class UserDTOConverter : Converter<User, UserDTO> {
    override fun convertToDTO(source: User): UserDTO {
        return UserDTO(
            username = source.username,
            password = source.password
        )
    }

    override fun convertFromDTO(source: UserDTO): User {
        return User(
            username = source.username,
            password = source.password,
            games = emptySet()
        )
    }
}

@Component
class UserDTOWithIdConverter : Converter<User, UserDTOWithId> {
    override fun convertToDTO(source: User): UserDTOWithId {
        return UserDTOWithId(
            id = source.id!!,
            username = source.username
        )
    }

    override fun convertFromDTO(source: UserDTOWithId): User {
        TODO("Not yet implemented")
    }
}