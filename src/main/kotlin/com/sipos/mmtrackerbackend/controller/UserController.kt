package com.sipos.mmtrackerbackend.controller

import com.sipos.mmtrackerbackend.dto.GameDTORequest
import com.sipos.mmtrackerbackend.dto.UserDTORequest
import com.sipos.mmtrackerbackend.service.GameService
import com.sipos.mmtrackerbackend.service.UserService
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.persistence.EntityNotFoundException

@RestController
@RequestMapping("/api/users")
class UserController(val userService: UserService, val gameService: GameService) {

    @ExceptionHandler(EntityNotFoundException::class, EmptyResultDataAccessException::class)
    fun handleException(): ResponseEntity<Unit> {
        return ResponseEntity.notFound().build()
    }

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