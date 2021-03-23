package com.sipos.mmtrackerbackend.service

import com.sipos.mmtrackerbackend.dto.AccountDTO
import com.sipos.mmtrackerbackend.model.User
import com.sipos.mmtrackerbackend.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AccountService(val userRepository: UserRepository) {
    fun login(account: AccountDTO): ResponseEntity<Unit> {
        return if (userRepository.existsByUsernameAndPassword(account.username, account.password)) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.badRequest().build()
        }
    }

    fun register(account: AccountDTO): ResponseEntity<Unit> {
        return if (userRepository.existsByUsername(account.username)) {
            ResponseEntity.badRequest().build()
        } else {
            userRepository.save(
                User(
                    username = account.username,
                    password = account.password,
                )
            )
            ResponseEntity.ok().build()
        }
    }
}