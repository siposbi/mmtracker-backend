package com.sipos.mmtrackerbackend.controller

import com.sipos.mmtrackerbackend.dto.*
import com.sipos.mmtrackerbackend.model.Game
import com.sipos.mmtrackerbackend.repository.GameRepository
import com.sipos.mmtrackerbackend.repository.MapRepository
import com.sipos.mmtrackerbackend.repository.UserRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.PageRequest
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
        val users = userRepository.findAll().map { userConverter.convertToResponse(it) }

        return ResponseEntity.ok(users)
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
    fun getGames(
        @PathVariable id: Long,
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = Integer.MAX_VALUE.toString()) size: Int,
    ): ResponseEntity<MutableMap<String, Any>> {
        val games = gameRepository.findByUserId(id, PageRequest.of(page, size))

        if (games.totalPages <= page) {
            return ResponseEntity.badRequest().build()
        }

        val response: MutableMap<String, Any> = HashMap()
        response["games"] = games.content.map { gameConverter.convertToResponse(it) }
        response["currentPage"] = games.number
        response["totalItems"] = games.totalElements
        response["totalPages"] = games.totalPages

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