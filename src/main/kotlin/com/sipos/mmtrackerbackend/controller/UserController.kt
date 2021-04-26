package com.sipos.mmtrackerbackend.controller

import com.sipos.mmtrackerbackend.dto.*
import com.sipos.mmtrackerbackend.model.Game
import com.sipos.mmtrackerbackend.model.User
import com.sipos.mmtrackerbackend.repository.GameRepository
import com.sipos.mmtrackerbackend.repository.MapRepository
import com.sipos.mmtrackerbackend.repository.UserRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.persistence.EntityNotFoundException

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userRepository: UserRepository,
    private val userConverter: UserConverter,
    private val gameRepository: GameRepository,
    private val gameConverter: GameConverter,
    private val mapRepository: MapRepository
) {

    @ExceptionHandler(EntityNotFoundException::class, EmptyResultDataAccessException::class)
    fun handleException(): ResponseEntity<Unit> {
        return ResponseEntity.notFound().build()
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<UserDTOResponse>> {
        val maps = userRepository.findAll()
        val response = mutableListOf<UserDTOResponse>()
        maps.forEach { user: User ->
            response.add(userConverter.convertToResponse(user))
        }
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<UserDTOResponse> {
        val userToBeReturned = userRepository.getOne(id)
        return ResponseEntity.ok(userConverter.convertToResponse(userToBeReturned))
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Unit> {
        userRepository.deleteById(id)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{id}/games")
    fun getGames(@PathVariable id: Long): ResponseEntity<List<GameDTOResponse>> {
        val games = gameRepository.findByUserId(id)
        val response = mutableListOf<GameDTOResponse>()
        games.forEach { game: Game ->
            response.add(gameConverter.convertToResponse(game))
        }
        return ResponseEntity.ok(response)
    }

    @PostMapping("/{id}/games")
    fun addGame(@PathVariable id: Long, @RequestBody game: GameDTORequest): ResponseEntity<GameDTOResponse> {
        val user = userRepository.getOne(id)
        val map = mapRepository.getOne(game.map_id)
        val gameSaved = gameRepository.save(
            Game(
                roundsWon = game.roundsWon,
                roundsLost = game.roundsLost,
                kills = game.kills,
                assists = game.assists,
                deaths = game.deaths,
                user = user,
                map = map,
            )
        )
        return ResponseEntity.ok(gameConverter.convertToResponse(gameSaved))
    }
}