package com.sipos.mmtrackerbackend.controller

import com.sipos.mmtrackerbackend.dto.MapDTORequest
import com.sipos.mmtrackerbackend.service.MapService
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.persistence.EntityNotFoundException

@RestController
@RequestMapping("/api/maps")
class MapController(private val mapService: MapService) {

    @ExceptionHandler(EntityNotFoundException::class, EmptyResultDataAccessException::class)
    fun handleException(): ResponseEntity<Unit> {
        return ResponseEntity.notFound().build()
    }

    @GetMapping
    fun findAll() = mapService.findAll()

    @PostMapping
    fun addMap(@RequestBody map: MapDTORequest) = mapService.add(map)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = mapService.getById(id)

    @PutMapping("/{id}")
    fun updateById(@PathVariable id: Long, @RequestBody map: MapDTORequest) = mapService.updateById(map, id)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) = mapService.deleteById(id)
}