package com.sipos.mmtrackerbackend.service

import com.sipos.mmtrackerbackend.dto.AccountDTO
import com.sipos.mmtrackerbackend.model.User
import com.sipos.mmtrackerbackend.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AccountService(val userRepository: UserRepository) {
    fun login(account: AccountDTO): Boolean {
        return userRepository.existsByUsernameAndPassword(account.username, account.password)
    }

    fun register(account: AccountDTO): Boolean {
        return if (userRepository.existsByUsername(account.username)) {
            false
        } else {
            userRepository.save(
                User(
                    username = account.username,
                    password = account.password,
                )
            )
            true
        }
    }
}