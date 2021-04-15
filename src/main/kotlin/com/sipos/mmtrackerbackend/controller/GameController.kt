package com.sipos.mmtrackerbackend.controller

import com.sipos.mmtrackerbackend.dto.GameDTORequest
import com.sipos.mmtrackerbackend.service.GameService
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.persistence.EntityNotFoundException

@RestController
@RequestMapping("/api/games")
class GameController(private val gameService: GameService) {

    @ExceptionHandler(EntityNotFoundException::class, EmptyResultDataAccessException::class)
    fun handleException(): ResponseEntity<Unit> {
        return ResponseEntity.notFound().build()
    }

    @GetMapping
    fun findAll() = gameService.findAll()

    @PostMapping
    fun addGame(@RequestBody game: GameDTORequest) = gameService.add(game)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = gameService.getById(id)

    @PutMapping("/{id}")
    fun updateById(@PathVariable id: Long, @RequestBody game: GameDTORequest) = gameService.updateById(game, id)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) = gameService.deleteById(id)
}