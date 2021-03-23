package com.sipos.mmtrackerbackend.dto

import com.sipos.mmtrackerbackend.model.Game
import org.springframework.stereotype.Component

data class GameDTORequest(
    var roundsWon: Int,
    var roundsLost: Int,
    var kills: Int,
    var assists: Int,
    var deaths: Int,
    var user_id: Long,
    var map_id: Long
)

data class GameDTOResponse(
    var id: Long,
    var roundsWon: Int,
    var roundsLost: Int,
    var kills: Int,
    var assists: Int,
    var deaths: Int,
    val user: UserDTOResponse,
    var map: MapDTOResponse
)

@Component
class GameConverter(val mapConverter: MapConverter, val userConverter: UserConverter) :
    Converter<Game, GameDTORequest, GameDTOResponse> {

    override fun convertFromRequest(source: GameDTORequest, id: Long): Game {
        TODO("Not yet implemented")
    }

    override fun convertToResponse(source: Game): GameDTOResponse {
        return GameDTOResponse(
            id = source.id!!,
            roundsWon = source.roundsWon,
            roundsLost = source.roundsLost,
            kills = source.kills,
            assists = source.assists,
            deaths = source.deaths,
            user = userConverter.convertToResponse(source.user),
            map = mapConverter.convertToResponse(source.map)
        )
    }
}