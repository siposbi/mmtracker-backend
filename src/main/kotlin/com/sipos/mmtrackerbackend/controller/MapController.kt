package com.sipos.mmtrackerbackend.controller

import com.sipos.mmtrackerbackend.model.Map
import com.sipos.mmtrackerbackend.repository.MapRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/maps")
class MapController(val mapRepository: MapRepository) {

    @GetMapping
    fun findAll() = mapRepository.findAll()

    @PostMapping
    fun addMap(@RequestBody map: Map) = mapRepository.save(map)

    @PutMapping("/{id}")
    fun updateMap(@PathVariable id: Long, @RequestBody map: Map) {
        assert(map.id == id)
        mapRepository.save(map)
    }

    @DeleteMapping("/{id}")
    fun deleteMap(@PathVariable id: Long) = mapRepository.deleteById(id)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = mapRepository.findById(id)
}