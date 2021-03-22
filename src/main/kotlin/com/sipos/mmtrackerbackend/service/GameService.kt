package com.sipos.mmtrackerbackend.service

import com.sipos.mmtrackerbackend.dto.GameConverter
import com.sipos.mmtrackerbackend.dto.GameDTORequest
import com.sipos.mmtrackerbackend.dto.GameDTOResponse
import com.sipos.mmtrackerbackend.model.Game
import com.sipos.mmtrackerbackend.repository.GameRepository
import org.springframework.stereotype.Service

@Service
class GameService(
    val gameRepository: GameRepository,
    val gameConverter: GameConverter
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
        TODO()
    }

    fun getById(id: Long): GameDTOResponse {
        val gameToBeReturned = gameRepository.getOne(id)
        return gameConverter.convertToResponse(gameToBeReturned)
    }

    fun updateById(game: GameDTORequest, id: Long): GameDTOResponse {
        TODO()
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

    fun addGameToUser(userId: Long, game: GameDTORequest): Any {
        TODO()
    }
}