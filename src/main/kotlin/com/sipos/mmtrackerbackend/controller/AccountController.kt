package com.sipos.mmtrackerbackend.controller

import com.sipos.mmtrackerbackend.dto.AccountDTO
import com.sipos.mmtrackerbackend.service.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/accounts")
class AccountController(private val accountService: AccountService) {

    @PostMapping("/login")
    fun login(@RequestBody account: AccountDTO): ResponseEntity<Unit> {
        return if (accountService.login(account)) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/register")
    fun register(@RequestBody account: AccountDTO): ResponseEntity<Unit> {
        return if (accountService.register(account)) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.badRequest().build()
        }
    }
}