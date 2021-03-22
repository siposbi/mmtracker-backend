package com.sipos.mmtrackerbackend.service

import com.sipos.mmtrackerbackend.dto.MapDTO
import com.sipos.mmtrackerbackend.dto.MapDTOConverter
import com.sipos.mmtrackerbackend.dto.MapDTOWithId
import com.sipos.mmtrackerbackend.dto.MapDTOWithIdConverter
import com.sipos.mmtrackerbackend.model.Map
import com.sipos.mmtrackerbackend.repository.MapRepository
import org.springframework.stereotype.Service

@Service
class MapService(
    val mapRepository: MapRepository,
    val mapDTOConverter: MapDTOConverter,
    val mapDTOWithIdConverter: MapDTOWithIdConverter
) {
    fun findAll(): MutableList<MapDTOWithId> {
        val maps = mapRepository.findAll()
        val response = mutableListOf<MapDTOWithId>()
        maps.forEach { map: Map ->
            response.add(mapDTOWithIdConverter.convertToDTO(map))
        }
        return response
    }

    fun add(map: MapDTO): MapDTOWithId {
        val mapToBeSaved = mapDTOConverter.convertFromDTO(map)
        val savedMap = mapRepository.save(mapToBeSaved)
        return mapDTOWithIdConverter.convertToDTO(savedMap)
    }

    fun getById(id: Long): MapDTOWithId {
        val mapToBeReturned = mapRepository.getOne(id)
        return mapDTOWithIdConverter.convertToDTO(mapToBeReturned)
    }

    fun updateById(map: MapDTO, id: Long): MapDTOWithId {
        val mapToBeUpdated = mapRepository.getOne(id)
        mapToBeUpdated.fileName = map.fileName
        mapToBeUpdated.name = map.name
        val savedMap = mapRepository.save(mapToBeUpdated)
        return mapDTOWithIdConverter.convertToDTO(savedMap)
    }

    fun deleteById(id: Long) {
        mapRepository.deleteById(id)
    }
}