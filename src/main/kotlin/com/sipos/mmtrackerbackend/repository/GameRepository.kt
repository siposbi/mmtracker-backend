package com.sipos.mmtrackerbackend.repository

import com.sipos.mmtrackerbackend.model.Game
import org.springframework.data.jpa.repository.JpaRepository

interface GameRepository : JpaRepository<Game, Long> {

    fun findByUserId(id: Long): List<Game>
}