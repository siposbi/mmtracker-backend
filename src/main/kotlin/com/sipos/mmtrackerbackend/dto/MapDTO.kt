package com.sipos.mmtrackerbackend.dto

import com.sipos.mmtrackerbackend.model.Map
import org.springframework.stereotype.Component

data class MapDTORequest(
    var fileName: String,
    var name: String
)

data class MapDTOResponse(
    var id: Long,
    var fileName: String,
    var name: String
)


@Component
class MapConverter : Converter<Map, MapDTORequest, MapDTOResponse> {

    override fun convertFromRequest(source: MapDTORequest, id: Long): Map {
        return Map(
            id = id,
            fileName = source.fileName,
            name = source.name
        )
    }

    override fun convertToResponse(source: Map): MapDTOResponse {
        return MapDTOResponse(
            id = source.id!!,
            fileName = source.fileName,
            name = source.name
        )
    }
}