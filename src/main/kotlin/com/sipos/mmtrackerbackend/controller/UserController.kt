package com.sipos.mmtrackerbackend.controller

import com.sipos.mmtrackerbackend.dto.GameDTORequest
import com.sipos.mmtrackerbackend.dto.UserDTORequest
import com.sipos.mmtrackerbackend.service.GameService
import com.sipos.mmtrackerbackend.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(val userService: UserService, val gameService: GameService) {

    @GetMapping
    fun findAll() = userService.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = userService.getById(id)

    @PutMapping("/{id}")
    fun updateById(@PathVariable id: Long, @RequestBody user: UserDTORequest) = userService.updateById(user, id)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) = userService.deleteById(id)

    @GetMapping("/{id}/games")
    fun getGames(@PathVariable id: Long) = gameService.getGamesOfUser(id)

    @PostMapping("/{id}/games")
    fun addGame(@PathVariable id: Long, @RequestBody game: GameDTORequest) = gameService.addGameToUser(id, game)
}