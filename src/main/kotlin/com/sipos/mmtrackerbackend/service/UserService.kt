package com.sipos.mmtrackerbackend.service

import com.sipos.mmtrackerbackend.dto.UserConverter
import com.sipos.mmtrackerbackend.dto.UserDTORequest
import com.sipos.mmtrackerbackend.dto.UserDTOResponse
import com.sipos.mmtrackerbackend.model.User
import com.sipos.mmtrackerbackend.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userConverter: UserConverter
) {
    fun findAll(): MutableList<UserDTOResponse> {
        val maps = userRepository.findAll()
        val response = mutableListOf<UserDTOResponse>()
        maps.forEach { user: User ->
            response.add(userConverter.convertToResponse(user))
        }
        return response
    }

    fun getById(id: Long): UserDTOResponse {
        val userToBeReturned = userRepository.getOne(id)
        return userConverter.convertToResponse(userToBeReturned)
    }

    fun updateById(user: UserDTORequest, id: Long): UserDTOResponse {
        val userToBeUpdated = userRepository.getOne(id)
        userToBeUpdated.password = user.password
        val savedUser = userRepository.save(userToBeUpdated)
        return userConverter.convertToResponse(savedUser)
    }

    fun deleteById(id: Long) {
        userRepository.deleteById(id)
    }
}