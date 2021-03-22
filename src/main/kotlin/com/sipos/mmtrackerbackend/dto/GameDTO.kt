package com.sipos.mmtrackerbackend.dto

import com.sipos.mmtrackerbackend.model.Game
import org.springframework.stereotype.Component

class GameDTO(
    var roundsWon: Int,
    var roundsLost: Int,
    var kills: Int,
    var assists: Int,
    var deaths: Int,
    var user: UserDTOWithId,
    var map: MapDTOWithId
)

@Component
class GameDTOConverter : Converter<Game, GameDTO> {
    override fun convertToDTO(source: Game): GameDTO {
        return GameDTO(
            roundsWon = source.roundsWon,
            roundsLost = source.roundsLost,
            kills = source.kills,
            assists = source.assists,
            deaths = source.deaths,
            user = UserDTOWithId(
                id = source.user.id!!,
                username = source.user.username
            ),
            map = MapDTOWithId(
                id = source.map.id!!,
                fileName = source.map.fileName,
                name = source.map.name
            )
        )
    }

    override fun convertFromDTO(source: GameDTO): Game {
        TODO("Not yet implemented")
    }
}