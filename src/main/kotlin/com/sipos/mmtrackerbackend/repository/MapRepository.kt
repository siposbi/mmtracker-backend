package com.sipos.mmtrackerbackend.repository

import com.sipos.mmtrackerbackend.model.Map
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MapRepository : JpaRepository<Map, Long> {

    fun existsByName(mapName: String): Boolean
    fun existsByFileName(fileName: String): Boolean
    fun findByName(mapName: String): Map
    fun findByFileName(fileName: String): Map
}