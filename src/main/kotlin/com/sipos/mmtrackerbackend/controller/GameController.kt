package com.sipos.mmtrackerbackend.controller

import com.sipos.mmtrackerbackend.dto.GameDTORequest
import com.sipos.mmtrackerbackend.model.Game
import com.sipos.mmtrackerbackend.service.GameService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/games")
class GameController(val gameService: GameService) {

    @GetMapping
    fun findAll() = gameService.findAll()

    @PostMapping
    fun addGame(@RequestBody game: GameDTORequest) = gameService.add(game)

    @PutMapping("/{id}")
    fun updateGame(@PathVariable id: Long, @RequestBody game: Game) = gameService.getById(id)

    @DeleteMapping("/{id}")
    fun deleteGame(@PathVariable id: Long, @RequestBody game: GameDTORequest) = gameService.updateById(game, id)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = gameService.deleteById(id)
}