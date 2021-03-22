package com.sipos.mmtrackerbackend.controller

import com.sipos.mmtrackerbackend.dto.UserDTO
import com.sipos.mmtrackerbackend.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(val userService: UserService) {

    @GetMapping
    fun findAll() = userService.findAll()

    @PostMapping
    fun addMap(@RequestBody user: UserDTO) = userService.add(user)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = userService.getById(id)

    @PutMapping("/{id}")
    fun updateById(@PathVariable id: Long, @RequestBody user: UserDTO) = userService.updateById(user, id)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) = userService.deleteById(id)
}