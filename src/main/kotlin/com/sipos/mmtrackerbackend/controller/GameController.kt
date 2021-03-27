package com.sipos.mmtrackerbackend.controller

import com.sipos.mmtrackerbackend.dto.GameDTORequest
import com.sipos.mmtrackerbackend.service.GameService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/games")
class GameController(val gameService: GameService) {

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