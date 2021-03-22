package com.sipos.mmtrackerbackend.controller

import com.sipos.mmtrackerbackend.dto.AccountDTO
import com.sipos.mmtrackerbackend.dto.UserDTO
import com.sipos.mmtrackerbackend.service.AccountService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController(val accountService: AccountService) {

    @PostMapping("/login")
    fun login(@RequestBody account: AccountDTO) = accountService.login(account)

    @PostMapping("/register")
    fun register(@RequestBody account: UserDTO) = accountService.register(account)
}