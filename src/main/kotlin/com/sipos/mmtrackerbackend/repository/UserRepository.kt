package com.sipos.mmtrackerbackend.repository

import com.sipos.mmtrackerbackend.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByUsernameAndPassword(username: String, password: String): List<User>
}