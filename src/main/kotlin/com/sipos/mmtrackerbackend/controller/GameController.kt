package com.sipos.mmtrackerbackend.controller

import com.sipos.mmtrackerbackend.model.Game
import com.sipos.mmtrackerbackend.repository.GameRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/games")
class GameController(val gameRepository: GameRepository) {

    @GetMapping
    fun findAll() = gameRepository.findAll()

    @PostMapping
    fun addGame(@RequestBody game: Game) = gameRepository.save(game)

    @PutMapping("/{id}")
    fun updateGame(@RequestParam id: Long, @RequestBody game: Game){
        assert(game.id == id)
        gameRepository.save(game)
    }

    @DeleteMapping("/{id}")
    fun deleteMap(@RequestParam id: Long) = gameRepository.deleteById(id)

    @GetMapping("/{id}")
    fun getById(@RequestParam id: Long) = gameRepository.findById(id)
}