package com.sipos.mmtrackerbackend.service

import com.sipos.mmtrackerbackend.dto.UserDTO
import com.sipos.mmtrackerbackend.dto.UserDTOConverter
import com.sipos.mmtrackerbackend.dto.UserDTOWithId
import com.sipos.mmtrackerbackend.dto.UserDTOWithIdConverter
import com.sipos.mmtrackerbackend.model.User
import com.sipos.mmtrackerbackend.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
    val userDTOConverter: UserDTOConverter,
    val userDTOWithIdConverter: UserDTOWithIdConverter
) {
    fun findAll(): MutableList<UserDTOWithId> {
        val maps = userRepository.findAll()
        val response = mutableListOf<UserDTOWithId>()
        maps.forEach { user: User ->
            response.add(userDTOWithIdConverter.convertToDTO(user))
        }
        return response
    }

    fun add(user: UserDTO): UserDTOWithId {
        val userToBeSaved = userDTOConverter.convertFromDTO(user)
        val savedUser = userRepository.save(userToBeSaved)
        return userDTOWithIdConverter.convertToDTO(savedUser)
    }

    fun getById(id: Long): UserDTOWithId {
        val userToBeReturned = userRepository.findById(id).get()
        return userDTOWithIdConverter.convertToDTO(userToBeReturned)
    }

    fun updateById(user: UserDTO, id: Long): UserDTOWithId {
        val userWithId = UserDTOWithId(
            id = id,
            username = user.username,
            emailAddress = user.emailAddress
        )
        val userToBeUpdated = userDTOWithIdConverter.convertFromDTO(userWithId)
        val savedUser = userRepository.save(userToBeUpdated)
        return userDTOWithIdConverter.convertToDTO(savedUser)
    }

    fun deleteById(id: Long) {
        userRepository.deleteById(id)
    }
}