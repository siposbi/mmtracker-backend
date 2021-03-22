package com.sipos.mmtrackerbackend.service

import com.sipos.mmtrackerbackend.dto.MapConverter
import com.sipos.mmtrackerbackend.dto.MapDTORequest
import com.sipos.mmtrackerbackend.dto.MapDTOResponse
import com.sipos.mmtrackerbackend.model.Map
import com.sipos.mmtrackerbackend.repository.MapRepository
import org.springframework.stereotype.Service

@Service
class MapService(
    val mapRepository: MapRepository,
    val mapConverter: MapConverter
) {
    fun findAll(): MutableList<MapDTOResponse> {
        val maps = mapRepository.findAll()
        val response = mutableListOf<MapDTOResponse>()
        maps.forEach { map: Map ->
            response.add(mapConverter.convertToResponse(map))
        }
        return response
    }

    fun add(map: MapDTORequest): MapDTOResponse {
        val savedMap = mapRepository.save(Map(fileName = map.fileName, name = map.name))
        return mapConverter.convertToResponse(savedMap)
    }

    fun getById(id: Long): MapDTOResponse {
        val mapToBeReturned = mapRepository.getOne(id)
        return mapConverter.convertToResponse(mapToBeReturned)
    }

    fun updateById(map: MapDTORequest, id: Long): MapDTOResponse {
        val mapToBeUpdated = mapRepository.getOne(id)
        mapToBeUpdated.fileName = map.fileName
        mapToBeUpdated.name = map.name
        val savedMap = mapRepository.save(mapToBeUpdated)
        return mapConverter.convertToResponse(savedMap)
    }

    fun deleteById(id: Long) {
        mapRepository.deleteById(id)
    }
}