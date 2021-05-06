package com.sipos.mmtrackerbackend.controller

import com.sipos.mmtrackerbackend.dto.GameConverter
import com.sipos.mmtrackerbackend.dto.GameDTORequest
import com.sipos.mmtrackerbackend.dto.GameDTOResponse
import com.sipos.mmtrackerbackend.repository.GameRepository
import com.sipos.mmtrackerbackend.repository.MapRepository
import com.sipos.mmtrackerbackend.repository.UserRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.persistence.EntityNotFoundException

@RestController
@RequestMapping("/api/games")
class GameController(
    private val gameRepository: GameRepository,
    private val gameConverter: GameConverter,
    private val userRepository: UserRepository,
    private val mapRepository: MapRepository
) {

    @ExceptionHandler(EntityNotFoundException::class, EmptyResultDataAccessException::class)
    fun handleException(): ResponseEntity<Unit> {
        return ResponseEntity.notFound().build()
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<GameDTOResponse>> {
        val games = gameRepository.findAll().map { gameConverter.convertToResponse(it) }

        return ResponseEntity.ok(games)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<GameDTOResponse> {
        val gameToBeReturned = gameRepository.getOne(id)

        return ResponseEntity.ok(gameConverter.convertToResponse(gameToBeReturned))
    }

    @PutMapping("/{id}")
    fun updateById(@PathVariable id: Long, @RequestBody game: GameDTORequest): ResponseEntity<GameDTOResponse> {
        val user = userRepository.getOne(game.user_id)
        val map = mapRepository.getOne(game.map_id)
        val gameToBeModified = gameRepository.getOne(id)
        gameToBeModified.roundsWon = game.roundsWon
        gameToBeModified.roundsLost = game.roundsLost
        gameToBeModified.kills = game.kills
        gameToBeModified.assists = game.assists
        gameToBeModified.deaths = game.deaths
        gameToBeModified.user = user
        gameToBeModified.map = map
        val savedGame = gameRepository.save(gameToBeModified)

        return ResponseEntity.ok(gameConverter.convertToResponse(savedGame))
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Unit> {
        gameRepository.deleteById(id)

        return ResponseEntity.ok().build()
    }
}