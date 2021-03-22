package com.sipos.mmtrackerbackend.service

import com.sipos.mmtrackerbackend.dto.GameDTO
import com.sipos.mmtrackerbackend.dto.GameDTOConverter
import com.sipos.mmtrackerbackend.model.Game
import com.sipos.mmtrackerbackend.repository.GameRepository
import org.springframework.stereotype.Service

@Service
class GameService(
    val gameRepository: GameRepository,
    val gameDTOConverter: GameDTOConverter
) {
    fun getGamesOfUser(id: Long): List<GameDTO> {
        val games = gameRepository.findByUserId(id)
        val response = mutableListOf<GameDTO>()
        games.forEach { game: Game ->
            response.add(gameDTOConverter.convertToDTO(game))
        }
        return response
    }

    fun addGameToUser(id: Long, gameDTO: GameDTO): Any {
        TODO()
    }


}