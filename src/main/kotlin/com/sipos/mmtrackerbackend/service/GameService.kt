package com.sipos.mmtrackerbackend.service

import com.sipos.mmtrackerbackend.dto.GameConverter
import com.sipos.mmtrackerbackend.dto.GameDTORequest
import com.sipos.mmtrackerbackend.dto.GameDTOResponse
import com.sipos.mmtrackerbackend.model.Game
import com.sipos.mmtrackerbackend.model.Map
import com.sipos.mmtrackerbackend.model.User
import com.sipos.mmtrackerbackend.repository.GameRepository
import com.sipos.mmtrackerbackend.repository.MapRepository
import com.sipos.mmtrackerbackend.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class GameService(
    val gameRepository: GameRepository,
    val gameConverter: GameConverter,
    val userRepository: UserRepository,
    val mapRepository: MapRepository
) {
    fun findAll(): MutableList<GameDTOResponse> {
        val games = gameRepository.findAll()
        val response = mutableListOf<GameDTOResponse>()
        games.forEach { game: Game ->
            response.add(gameConverter.convertToResponse(game))
        }
        return response
    }

    fun add(game: GameDTORequest): GameDTOResponse {
        return addGameToUser(game.user_id, game)
    }

    fun getById(id: Long): GameDTOResponse {
        val gameToBeReturned = gameRepository.getOne(id)
        return gameConverter.convertToResponse(gameToBeReturned)
    }

    fun updateById(game: GameDTORequest, id: Long): GameDTOResponse {
        val user = userRepository.getOne(game.user_id)
        val map = mapRepository.getOne(game.map_id)
        return updateOrAdd(user, map, game)
    }

    fun deleteById(id: Long) {
        gameRepository.deleteById(id)
    }

    fun getGamesOfUser(id: Long): List<GameDTOResponse> {
        val games = gameRepository.findByUserId(id)
        val response = mutableListOf<GameDTOResponse>()
        games.forEach { game: Game ->
            response.add(gameConverter.convertToResponse(game))
        }
        return response
    }

    fun addGameToUser(userId: Long, game: GameDTORequest): GameDTOResponse {
        val user = userRepository.getOne(userId)
        val map = mapRepository.getOne(game.map_id)
        return updateOrAdd(user, map, game)
    }

    private fun updateOrAdd(user: User, map: Map, game: GameDTORequest): GameDTOResponse{
        val gameToBeSaved = Game(
            roundsWon = game.roundsWon,
            roundsLost = game.roundsLost,
            kills = game.kills,
            assists = game.assists,
            deaths = game.deaths,
            user = user,
            map = map,
        )
        val mapSaved = gameRepository.save(gameToBeSaved)
        return gameConverter.convertToResponse(mapSaved)
    }
}