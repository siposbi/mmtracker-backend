package com.sipos.mmtrackerbackend.controller

import com.sipos.mmtrackerbackend.dto.MapDTO
import com.sipos.mmtrackerbackend.service.MapService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/maps")
class MapController(val mapService: MapService) {

    @GetMapping
    fun findAll() = mapService.findAll()

    @PostMapping
    fun addMap(@RequestBody map: MapDTO) = mapService.add(map)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = mapService.getById(id)

    @PutMapping("/{id}")
    fun updateById(@PathVariable id: Long, @RequestBody map: MapDTO) = mapService.updateById(map, id)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) = mapService.deleteById(id)
}