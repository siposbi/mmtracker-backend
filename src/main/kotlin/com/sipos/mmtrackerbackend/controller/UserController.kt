package com.sipos.mmtrackerbackend.controller

import com.sipos.mmtrackerbackend.model.User
import com.sipos.mmtrackerbackend.repository.UserRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(val userRepository: UserRepository) {

    @GetMapping
    fun findAll() = userRepository.findAll()

    @PostMapping
    fun addUser(@RequestBody user: User) = userRepository.save(user)

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody user: User) {
        assert(user.id == id)
        userRepository.save(user)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) = userRepository.deleteById(id)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = userRepository.findById(id)
}