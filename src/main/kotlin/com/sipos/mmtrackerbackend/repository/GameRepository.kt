package com.sipos.mmtrackerbackend.repository

import com.sipos.mmtrackerbackend.model.Game
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface GameRepository : JpaRepository<Game, Long> {

    fun findByUserId(id: Long, pageable: Pageable): Page<Game>
}