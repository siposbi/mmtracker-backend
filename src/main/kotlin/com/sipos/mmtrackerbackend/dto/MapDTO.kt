package com.sipos.mmtrackerbackend.dto

import com.sipos.mmtrackerbackend.model.Map
import org.springframework.stereotype.Component

class MapDTO(
    var fileName: String,
    var name: String
)

class MapDTOWithId(
    var id: Long,
    var fileName: String,
    var name: String
)

@Component
class MapDTOConverter : Converter<Map, MapDTO> {
    override fun convertToDTO(source: Map): MapDTO {
        return MapDTO(
            fileName = source.fileName,
            name = source.name
        )
    }

    override fun convertFromDTO(source: MapDTO): Map {
        return Map(
            fileName = source.fileName,
            name = source.name,
            games = emptySet()
        )
    }
}

@Component
class MapDTOWithIdConverter : Converter<Map, MapDTOWithId> {
    override fun convertToDTO(source: Map): MapDTOWithId {
        return MapDTOWithId(
            id = source.id!!,
            fileName = source.fileName,
            name = source.name
        )
    }

    override fun convertFromDTO(source: MapDTOWithId): Map {
        return Map(
            id = source.id,
            fileName = source.fileName,
            name = source.name,
            games = emptySet()
        )
    }
}