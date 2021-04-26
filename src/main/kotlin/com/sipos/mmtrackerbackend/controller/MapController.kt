package com.sipos.mmtrackerbackend.controller

import com.sipos.mmtrackerbackend.dto.MapConverter
import com.sipos.mmtrackerbackend.dto.MapDTORequest
import com.sipos.mmtrackerbackend.dto.MapDTOResponse
import com.sipos.mmtrackerbackend.model.Map
import com.sipos.mmtrackerbackend.repository.MapRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.persistence.EntityNotFoundException

@RestController
@RequestMapping("/api/maps")
class MapController(
    private val mapRepository: MapRepository,
    private val mapConverter: MapConverter
) {

    @ExceptionHandler(EntityNotFoundException::class, EmptyResultDataAccessException::class)
    fun handleException(): ResponseEntity<Unit> {
        return ResponseEntity.notFound().build()
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<MapDTOResponse>> {
        val maps = mapRepository.findAll()
        val response = mutableListOf<MapDTOResponse>()
        maps.forEach { map: Map ->
            response.add(mapConverter.convertToResponse(map))
        }
        return ResponseEntity.ok(response)
    }

    @PostMapping
    fun addMap(@RequestBody map: MapDTORequest): ResponseEntity<MapDTOResponse> {
        if (mapRepository.existsByName(map.name)) {
            val response = mapConverter.convertToResponse(mapRepository.findByName(map.name))
            return ResponseEntity.ok(response)
        }
        if (mapRepository.existsByFileName(map.fileName)) {
            val response = mapConverter.convertToResponse(mapRepository.findByFileName(map.fileName))
            return ResponseEntity.ok(response)
        }
        val savedMap = mapRepository.save(Map(fileName = map.fileName, name = map.name))
        return ResponseEntity.ok(mapConverter.convertToResponse(savedMap))
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<MapDTOResponse> {
        val mapToBeReturned = mapRepository.getOne(id)
        return ResponseEntity.ok(mapConverter.convertToResponse(mapToBeReturned))
    }

    @PutMapping("/{id}")
    fun updateById(@PathVariable id: Long, @RequestBody map: MapDTORequest): ResponseEntity<MapDTOResponse> {
        val mapToBeUpdated = mapRepository.getOne(id)
        mapToBeUpdated.fileName = map.fileName
        mapToBeUpdated.name = map.name
        val savedMap = mapRepository.save(mapToBeUpdated)
        return ResponseEntity.ok(mapConverter.convertToResponse(savedMap))
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Unit> {
        mapRepository.deleteById(id)
        return ResponseEntity.ok().build()
    }
}