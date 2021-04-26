package com.sipos.mmtrackerbackend.controller

import com.sipos.mmtrackerbackend.dto.UserDTORequest
import com.sipos.mmtrackerbackend.model.User
import com.sipos.mmtrackerbackend.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/accounts")
class AccountController(private val userRepository: UserRepository) {
    private val encoder = BCryptPasswordEncoder()

    @PostMapping("/login")
    fun login(@RequestBody account: UserDTORequest): ResponseEntity<Unit> {
        if (userRepository.existsByUsername(account.username)) {
            val user = userRepository.findByUsername(account.username)
            if (encoder.matches(account.password, user.password)) {
                return ResponseEntity.ok().build()
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
    }

    @PostMapping("/register")
    fun register(@RequestBody account: UserDTORequest): ResponseEntity<Unit> {
        if (userRepository.existsByUsername(account.username)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build()
        }
        userRepository.save(User(username = account.username, password = encoder.encode(account.password)))
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}