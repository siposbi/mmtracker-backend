package com.sipos.mmtrackerbackend.service

import com.sipos.mmtrackerbackend.dto.AccountDTO
import com.sipos.mmtrackerbackend.dto.UserDTO
import com.sipos.mmtrackerbackend.dto.UserDTOConverter
import com.sipos.mmtrackerbackend.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class AccountService(val userRepository: UserRepository, val userDTOConverter: UserDTOConverter) {
    fun login(account: AccountDTO): HttpStatus {
        return if (userRepository.findByUsernameAndPassword(account.username, account.password).any()) {
            HttpStatus.ACCEPTED
        } else {
            HttpStatus.FORBIDDEN
        }
    }

    fun register(account: UserDTO): HttpStatus {
        userRepository.save(userDTOConverter.convertFromDTO(account))
        return HttpStatus.ACCEPTED
    }
}