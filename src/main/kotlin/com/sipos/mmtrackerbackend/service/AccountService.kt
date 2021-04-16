package com.sipos.mmtrackerbackend.service

import com.sipos.mmtrackerbackend.dto.AccountDTO
import com.sipos.mmtrackerbackend.model.User
import com.sipos.mmtrackerbackend.repository.UserRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AccountService(private val userRepository: UserRepository) {
    private val encoder = BCryptPasswordEncoder()

    fun login(account: AccountDTO): Boolean {
        return try {
            val user = userRepository.findByUsername(account.username)
            encoder.matches(account.password, user.password)
        } catch (e: EmptyResultDataAccessException) {
            false
        }
    }

    fun register(account: AccountDTO): Boolean {
        return if (userRepository.existsByUsername(account.username)) {
            false
        } else {
            userRepository.save(
                User(
                    username = account.username,
                    password = encoder.encode(account.password),
                )
            )
            true
        }
    }
}